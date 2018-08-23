package com.ubo.terminal.handler;

import com.ubo.common.terminal.*;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dudu.service.common.CacheService;
import dudu.service.common.SaveFileService;
import dudu.service.core.ProtocolDecoder;
import dudu.service.core.Tuple2;
import dudu.service.core.terminal.LogoutMessage;
import dudu.service.core.utils.Utils;

import com.ubo.terminal.InvalidMessageException;
import com.ubo.terminal.SessionChannelHandler;
import org.springframework.beans.BeanUtils;

public class BasicProtocolDecoder implements ProtocolDecoder {

    private static final Logger LOG = LoggerFactory.getLogger(BasicProtocolDecoder.class);
    private SimpleDateFormat sdf;
    private CacheService cacheService;
    private int recordTimeOut;
    private SaveFileService saveFileService;

    public BasicProtocolDecoder() {

    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public CacheService getCacheService() {
        return cacheService;
    }

    public void setCacheService(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    public int getRecordTimeOut() {
        return recordTimeOut;
    }

    public void setRecordTimeOut(int recordTimeOut) {
        this.recordTimeOut = recordTimeOut;
    }

    public SaveFileService getSaveFileService() {
        return saveFileService;
    }

    public void setSaveFileService(SaveFileService saveFileService) {
        this.saveFileService = saveFileService;
    }

    private Object decodeLoginMessage(
            UboTerminalMessage tMsg,
            ChannelHandlerContext ctx) throws InvalidMessageException {

        String[] parts = tMsg.getBody().split(";");
        if (parts.length != 4) {
            throw new InvalidMessageException(String.format(
                    "Login[%s] body doesn't contain <9 parts.",
                    tMsg.getCmd()));
        }

        UboLoginMessage loginMsg = new UboLoginMessage();
        SessionChannelHandler.Session session = ctx.pipeline().get(SessionChannelHandler.class).getSession();
        BeanUtils.copyProperties(tMsg, loginMsg);
        loginMsg.setType("LOGIN");
        loginMsg.setSessionToken(session.getToken());
        try {
            loginMsg.setCreateTime(sdf.parse(tMsg.getSerial()));
        } catch (Exception e) {
            LOG.error(Utils.getThrowableInfo(e));
            loginMsg.setCreateTime(new Date());
        }

        loginMsg.setDeviceName(parts[0]);
        loginMsg.setToken(parts[1]);
        loginMsg.setLo(parts[2]);
        loginMsg.setLa(parts[3]);

        return loginMsg;
    }

    private Object decodeResponsMessage(
            UboTerminalMessage tMsg,
            ChannelHandlerContext ctx) throws InvalidMessageException {

        String[] parts = tMsg.getBody().split(";");
        if (parts.length < 9) {
            throw new InvalidMessageException(String.format(
                    "Login[%s] body doesn't contain <9 parts.",
                    tMsg.getCmd()));
        }

        ResponsMessage responsMessage = new ResponsMessage();
        BeanUtils.copyProperties(tMsg, responsMessage);
        try {
            responsMessage.setCreateTime(sdf.parse(tMsg.getSerial()));
        } catch (Exception e) {
            LOG.error(Utils.getThrowableInfo(e));
            responsMessage.setCreateTime(new Date());
        }
        responsMessage.setState(parts[0]);
        responsMessage.setToken(parts[1]);

        return responsMessage;
    }



    private Object decodeResponMessage(
            UboTerminalMessage tMsg,
            ChannelHandlerContext ctx) throws InvalidMessageException {

        String[] parts = tMsg.getBody().split(";");
        if (parts.length != 5) {
            throw new InvalidMessageException(String.format(
                    "Login[%s] body doesn't contain <9 parts.",
                    tMsg.getCmd()));
        }
        BasicResponMessage responMessage = new BasicResponMessage();
        BeanUtils.copyProperties(tMsg, responMessage);
        try {
            responMessage.setCreateTime(sdf.parse(tMsg.getSerial()));
        } catch (Exception e) {
            LOG.error(Utils.getThrowableInfo(e));
            responMessage.setCreateTime(new Date());
        }
        responMessage.setType("STRAPREQUEST");
        responMessage.setDeviceName(parts[0]);
        responMessage.setToken(parts[1]);
        responMessage.setLo(parts[2]);
        responMessage.setLg(parts[3]);
        responMessage.setSessionId(parts[4]);
        return responMessage;
    }

    private Object decodeMarryMessage(
            UboTerminalMessage tMsg,
            ChannelHandlerContext ctx) throws InvalidMessageException {

        String[] parts = tMsg.getBody().split(";");
        if (parts.length != 7) {
            throw new InvalidMessageException(String.format(
                    "Login[%s] body doesn't contain <9 parts.",
                    tMsg.getCmd()));
        }

        MarryRequestMessage responMessage = new MarryRequestMessage();
        BeanUtils.copyProperties(tMsg, responMessage);
        try {
            responMessage.setCreateTime(sdf.parse(tMsg.getSerial()));
        } catch (Exception e) {
            LOG.error(Utils.getThrowableInfo(e));
            responMessage.setCreateTime(new Date());
        }

        responMessage.setType("MARRY");
        responMessage.setReserve(parts[0]);
        responMessage.setGunId(parts[1]);
        responMessage.setTime(parts[2]);
        responMessage.setLo(parts[3]);
        responMessage.setLg(parts[4]);
        responMessage.setFailType(parts[5]);
        responMessage.setSessionId(parts[6]);
        return responMessage;
    }

    private Object decodeInWarehouseMessage(
            UboTerminalMessage tMsg,
            ChannelHandlerContext ctx) throws InvalidMessageException {

        String[] parts = tMsg.getBody().split(";");
        if (parts.length != 7) {
            throw new InvalidMessageException(String.format(
                    "Login[%s] body doesn't contain <9 parts.",
                    tMsg.getCmd()));
        }

        InWarehouseMessage responMessage = new InWarehouseMessage();
        BeanUtils.copyProperties(tMsg, responMessage);
        try {
            responMessage.setCreateTime(sdf.parse(tMsg.getSerial()));
        } catch (Exception e) {
            LOG.error(Utils.getThrowableInfo(e));
            responMessage.setCreateTime(new Date());
        }
        responMessage.setType("INWAREHOUSE");
        responMessage.setReserve(parts[0]);
        responMessage.setBluetoothMac(parts[1]);
        responMessage.setWarehousingTime(parts[2]);
        responMessage.setLo(parts[3]);
        responMessage.setLa(parts[4]);
        responMessage.setFailReason(parts[5]);
        responMessage.setAuthCode(parts[6]);
        return responMessage;
    }

    private Object decodeGpsMessage(
            UboTerminalMessage tMsg,
            ChannelHandlerContext ctx) throws InvalidMessageException {

        String[] parts = tMsg.getBody().split(";");
        if (!(parts.length == 6 || parts.length == 7 || parts.length == 8)) {
            throw new InvalidMessageException(String.format(
                    "Gps[%s] body doesn't contain 6|7|8 parts.",
                    tMsg.getCmd()));
        }

        UboGpsLbsMessage gpsLbsMsg = new UboGpsLbsMessage();

        gpsLbsMsg.setType("GPSLBS");
        gpsLbsMsg.setMsgId(tMsg.getSerial());
        gpsLbsMsg.setEqId(tMsg.getTid());
        try {
            gpsLbsMsg.setT(sdf.parse(tMsg.getSerial().substring(0, 14)));
        } catch (Exception e) {
            LOG.error(Utils.getThrowableInfo(e));
            gpsLbsMsg.setT(new Date());
        }

        gpsLbsMsg.setFireType(Byte.parseByte(parts[0]));

        if (parts[1] != null && !parts[1].isEmpty()) {
            gpsLbsMsg.setGpsOn(true);

            String[] lalo = parts[1].split("-");
            if (lalo.length != 2) {
                throw new InvalidMessageException(String.format(
                        "Gps[%s] gps doesn't contain 2 parts.",
                        tMsg.getCmd()));
            }
            gpsLbsMsg.setLongitude(Float.parseFloat(lalo[0]));
            gpsLbsMsg.setLatitude(Float.parseFloat(lalo[1]));

        } else {
            gpsLbsMsg.setGpsOn(false);
        }

        gpsLbsMsg.setVoltage(Float.parseFloat(parts[2]));
        gpsLbsMsg.setEQuantity(Integer.parseInt(parts[3]));

        if (parts[4] != null && !parts[4].isEmpty()) {
            String[] lbs = parts[4].split("!");
            if (lbs.length < 1) {
                throw new InvalidMessageException(String.format(
                        "Gps[%s] lbs doesn't contain less than 1 part.",
                        tMsg.getCmd()));
            }

            List<LbsBean> list = new ArrayList<LbsBean>();
            for (String l : lbs) {
                String[] lac_cid = l.split("/");
                if (lac_cid.length != 2 & lac_cid.length != 3) {
                    throw new InvalidMessageException(String.format(
                            "Gps[%s] lac_cid doesn't contain 2 parts.",
                            tMsg.getCmd()));
                }
                //默认信号强度为-200如果老版本友娃卡则为-200，新版本这使用上传值
                int signalStrength = -200;
                if (lac_cid.length == 3) {
                    signalStrength = Integer.parseInt(lac_cid[2]);
                }
                LbsBean bean = new LbsBean(
                        Integer.parseInt(lac_cid[1]),
                        Integer.parseInt(lac_cid[0]),
                        signalStrength);
                list.add(bean);

            }

            gpsLbsMsg.setLbs(list);
        }

        gpsLbsMsg.setMnc(Short.parseShort(parts[5]));

        if (parts.length == 8) {
            List<WiFiBean> list = new ArrayList<WiFiBean>();

            String[] wifi = parts[6].split("!");

            for (String wf : wifi) {
                String[] item = wf.split("/");

                if (item.length != 3) {
                    LOG.debug(String.format(
                            "Gps[%s] wifi doesn't contain 3 parts.",
                            tMsg.getCmd()));
                    break;
                }

                WiFiBean bean = new WiFiBean();
                bean.setMac(item[0]);
                bean.setTime(item[1]);
                bean.setSingalstrength(item[2]);
                list.add(bean);
            }

            gpsLbsMsg.setWifi(list);
        }

        return gpsLbsMsg;
    }

    private Object decodeHeartbeatMessage(
            UboTerminalMessage tMsg,
            ChannelHandlerContext ctx) throws InvalidMessageException {

        String[] item = tMsg.getBody().split(";");
        if (item.length != 10) {
            throw new InvalidMessageException(String.format(
                    "Heartbeat[%s] body doesn't contain 4|5 parts.",
                    tMsg.getCmd()));
        }
        SessionChannelHandler.Session session = ctx.pipeline().get(SessionChannelHandler.class).getSession();
        UboHeartbeatMessage bean = new UboHeartbeatMessage();

        BeanUtils.copyProperties(tMsg, bean);

        try {
            bean.setCreateTime(sdf.parse(tMsg.getSerial()));
        } catch (Exception e) {
            LOG.error(Utils.getThrowableInfo(e));
            bean.setCreateTime(new Date());
        }

        bean.setSessionToken(session.getEqId());
        bean.setRealTimeState(item[0]);
        bean.setState(item[1]);
        bean.setBluetoothMac(item[2]);
        bean.setLo(item[3]);
        bean.setLa(item[4]);
        bean.setAreaCode(item[5]);
        bean.setBatteryPower(item[6]);
        bean.setDeviceBatteryPower(item[7]);
        bean.setException(item[8]);
        bean.setAuthCode(item[9]);
        bean.setType("HB");

        return bean;
    }

    private Object decodeLocationMessage(UboTerminalMessage tMsg, ChannelHandlerContext ctx) throws InvalidMessageException {

        String[] parts = tMsg.getBody().split(";");
        if (!(parts.length == 3 || parts.length == 4 || parts.length == 5)) {
            throw new InvalidMessageException(String.format(
                    "Location[%s] body doesn't contain 3|4|5 parts.",
                    tMsg.getCmd()));
        }

        UboGpsLbsMessage gpsLbsMsg = new UboGpsLbsMessage();

        gpsLbsMsg.setType("GPSLBS");
        gpsLbsMsg.setMsgId(tMsg.getSerial());
        gpsLbsMsg.setEqId(tMsg.getTid());
        try {
            gpsLbsMsg.setT(sdf.parse(tMsg.getSerial().substring(0, 14)));
        } catch (Exception e) {
            LOG.error(Utils.getThrowableInfo(e));
            gpsLbsMsg.setT(new Date());
        }

        //3 : APP request
        gpsLbsMsg.setFireType((byte) 2);

        if (parts[0] != null && !parts[0].isEmpty()) {
            gpsLbsMsg.setGpsOn(true);

            String[] lalo = parts[0].split("-");
            if (lalo.length != 2) {
                throw new InvalidMessageException(String.format(
                        "Location[%s] gps doesn't contain 2 parts.",
                        tMsg.getCmd()));
            }
            gpsLbsMsg.setLongitude(Float.parseFloat(lalo[0]));
            gpsLbsMsg.setLatitude(Float.parseFloat(lalo[1]));

        } else {
            gpsLbsMsg.setGpsOn(false);
        }

        if (parts[1] != null && !parts[1].isEmpty()) {
            String[] lbs = parts[1].split("!");
            if (lbs.length < 1) {
                throw new InvalidMessageException(String.format(
                        "Location[%s] lbs doesn't contain less than 1 part.",
                        tMsg.getCmd()));
            }

            List<LbsBean> list = new ArrayList<LbsBean>();
            for (String l : lbs) {
                if (l == null || l.isEmpty()) {
                    continue;
                }
                String[] lac_cid = l.split("/");
                if (lac_cid.length != 2 & lac_cid.length != 3) {
                    throw new InvalidMessageException(String.format(
                            "Location[%s] lac_cid doesn't contain 2 parts.",
                            tMsg.getCmd()));
                }

                int signalStrength = -200;
                if (lac_cid.length == 3) {
                    signalStrength = Integer.parseInt(lac_cid[2]);
                }
                LbsBean bean = new LbsBean(
                        Integer.parseInt(lac_cid[1]),
                        Integer.parseInt(lac_cid[0]),
                        signalStrength);
                list.add(bean);
            }

            gpsLbsMsg.setLbs(list);
        }

        gpsLbsMsg.setMnc(Short.parseShort(parts[2]));

        if (parts.length == 5) {
            List<WiFiBean> list = new ArrayList<WiFiBean>();

            String[] wifi = parts[3].split("!");

            for (String wf : wifi) {
                String[] item = wf.split("/");

                if (item.length != 3) {
                    LOG.debug(String.format(
                            "Gps[%s] wifi doesn't contain 3 parts.",
                            tMsg.getCmd()));
                    break;
                }

                WiFiBean bean = new WiFiBean();
                bean.setMac(item[0]);
                bean.setTime(item[1]);
                bean.setSingalstrength(item[2]);
                list.add(bean);
            }

            gpsLbsMsg.setWifi(list);
        }

        return gpsLbsMsg;
    }


    private Object decodeGuardAckMessage(
            UboTerminalMessage tMsg,
            ChannelHandlerContext ctx) throws InvalidMessageException {

        String[] parts = tMsg.getBody().split("/");
        if (parts.length != 2) {
            throw new InvalidMessageException(String.format(
                    "GuardAreaAck[%s] body doesn't contain 2 parts.",
                    tMsg.getCmd()));
        }

        UboGuardAreaAckMessage guardAck = new UboGuardAreaAckMessage();

        guardAck.setType("GUARDAREA-ACK");
        guardAck.setMsgId(tMsg.getSerial());
        guardAck.setEqId(tMsg.getTid());

        List<Tuple2<String, Boolean>> list = new ArrayList<Tuple2<String, Boolean>>(2);
        for (String part : parts) {
            if (part != null && !part.isEmpty()) {

                String[] idNstatus = part.split(";");
                if (idNstatus.length != 2) {
                    throw new InvalidMessageException(String.format(
                            "GuardAreaAck[%s] idNstatus doesn't contain 2 parts.",
                            tMsg.getCmd()));
                }

                if (idNstatus[0] != null &&
                        !idNstatus[0].isEmpty() &&
                        !idNstatus[0].equals("0")) {

                    if (idNstatus[1].equals("0")) {
                        //success
                        Tuple2<String, Boolean> t2 = new Tuple2<String, Boolean>(idNstatus[0], Boolean.TRUE);
                        list.add(t2);
                    } else {
                        //failure
                        Tuple2<String, Boolean> t2 = new Tuple2<String, Boolean>(idNstatus[0], Boolean.FALSE);
                        list.add(t2);
                    }
                }
            }
        }

        guardAck.setAckList(list);

        return guardAck;
    }


    private Object decodeGuardMessage(
            UboTerminalMessage tMsg,
            ChannelHandlerContext ctx) throws InvalidMessageException {

        String[] parts = tMsg.getBody().split(";");
        if (parts.length != 3) {
            throw new InvalidMessageException(String.format(
                    "GuardAreaInd[%s] body doesn't contain 3 parts.",
                    tMsg.getCmd()));
        }

        UboGuardAreaIndMessage guardMsg = new UboGuardAreaIndMessage();

        guardMsg.setType("GUARD-AREA-IND");
        guardMsg.setMsgId(tMsg.getSerial());
        guardMsg.setEqId(tMsg.getTid());
        try {
            guardMsg.setT(sdf.parse(tMsg.getSerial().substring(0, 14)));
        } catch (Exception e) {
            LOG.error(Utils.getThrowableInfo(e));
            guardMsg.setT(new Date());
        }

        guardMsg.setGuardId(parts[0]);

        if (parts[1].equals("0")) {
            guardMsg.setGuardType("out");
        } else if (parts[1].equals("1")) {
            guardMsg.setGuardType("in");
        } else {
            throw new InvalidMessageException(String.format(
                    "GuardAreaInd[%s] pars[0] is not '0' or '1'.",
                    tMsg.getCmd()));
        }

        if (parts[2] != null && !parts[2].isEmpty()) {
            String[] lalo = parts[2].split("-");
            if (lalo.length != 2) {
                throw new InvalidMessageException(String.format(
                        "Gps[%s] gps doesn't contain 2 parts.",
                        tMsg.getCmd()));
            }
            guardMsg.setLongitude(Float.parseFloat(lalo[0]));
            guardMsg.setLatitude(Float.parseFloat(lalo[1]));
        } else {
            throw new InvalidMessageException(String.format(
                    "GuardAreaInd[%s] pars[1] is not lo and la.",
                    tMsg.getCmd()));
        }

        return guardMsg;
    }

    private Object decodeSOSMessage(
            UboTerminalMessage tMsg,
            ChannelHandlerContext ctx) throws InvalidMessageException {

        SOSRequestMessage bean = new SOSRequestMessage();
        BeanUtils.copyProperties(tMsg, bean);

        String[] item = tMsg.getBody().split(";");
        if (item.length != 6) {
            throw new InvalidMessageException(String.format(
                    "Heartbeat[%s] body doesn't contain 4|5 parts.",
                    tMsg.getCmd()));
        }

        BeanUtils.copyProperties(tMsg, bean);
        bean.setType("SOSMAG");
        bean.setReserve(item[0]);
        bean.setBluetoothMac(item[1]);
        bean.setLo(item[2]);
        bean.setLa(item[3]);
        bean.setAreaCode(item[4]);
        bean.setAuthCode(item[5]);
        try {
            bean.setCreateTime(sdf.parse(tMsg.getSerial().substring(0, 14)));
        } catch (Exception e) {
            LOG.error(Utils.getThrowableInfo(e));
            bean.setCreateTime(new Date());
        }

        return bean;
    }


    /**
     * 获得一个UUID
     *
     * @return String UUID
     */
    public static synchronized String getUUID() {
        String s = UUID.randomUUID().toString();
        //去掉“-”符号 
        return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
    }

    private Object decodeLogOutMessage(
            UboTerminalMessage tMsg,
            ChannelHandlerContext ctx) throws InvalidMessageException {

        String[] parts = tMsg.getBody().split(";");
        if (parts.length != 3) {
            throw new InvalidMessageException(String.format(
                    "LogoutInd[%s] body doesn't contain 3 parts.",
                    tMsg.getCmd()));
        }

        SessionChannelHandler.Session session = ctx.pipeline().get(SessionChannelHandler.class).getSession();

        if (parts[0].equals("0") || parts[0].equals("1")) {
            LogoutMessage logoutMsg = new LogoutMessage();
            logoutMsg.setMsgId(tMsg.getSerial());
            logoutMsg.setEqId(session.getEqId());
            logoutMsg.setSessionToken(session.getToken());
            logoutMsg.setType("LOGOUT");
            return logoutMsg;
        } else if (parts[0].equals("2")) {
            UboLowbatMessage lowbatMsg = new UboLowbatMessage();
            lowbatMsg.setMsgId(tMsg.getSerial());
            lowbatMsg.setEqId(session.getEqId());
            lowbatMsg.setType("LOWBAT");
            lowbatMsg.setT(new Date());
            return lowbatMsg;
        }
        return null;
    }

    @SuppressWarnings("unused")
    private Object decodeHeatbeatMessage(
            UboTerminalMessage tMsg,
            ChannelHandlerContext ctx) throws InvalidMessageException {

        String[] parts = tMsg.getBody().split(";");
        if (parts.length != 4) {
            throw new InvalidMessageException(String.format(
                    "BatteryInd[%s] body doesn't contain 4 parts.",
                    tMsg.getCmd()));
        }

        SessionChannelHandler.Session session = ctx.pipeline().get(SessionChannelHandler.class).getSession();

        UboBatteryMessage batMsg = new UboBatteryMessage();
        batMsg.setMsgId(tMsg.getSerial());
        batMsg.setEqId(session.getEqId());
        batMsg.setType("BATTERY");
        batMsg.setBat(parts[1]);

        return batMsg;
    }

    private Object decodeDownRecordRespMessage(
            UboTerminalMessage tMsg,
            ChannelHandlerContext ctx) throws InvalidMessageException {

        String[] parts = tMsg.getBody().split(";");

        if (parts.length < 3) {
            throw new InvalidMessageException(String.format(
                    "DownRecordResp[%s] body doesn't contain <3 parts.",
                    tMsg.getCmd()));
        }

        SessionChannelHandler.Session session = ctx.pipeline().get(SessionChannelHandler.class).getSession();

        UboDownRecordRespMessage downRecordRespMessage = new UboDownRecordRespMessage();
        downRecordRespMessage.setMsgId(tMsg.getSerial());
        downRecordRespMessage.setType("D-RECORD-RESP");
        downRecordRespMessage.setEqId(session.getEqId());
        downRecordRespMessage.setTotalPackageNo(parts[0]);
        downRecordRespMessage.setPackageNumber(parts[1]);
        downRecordRespMessage.setResult(parts[2]);
        if (parts.length >= 4) {
            downRecordRespMessage.setSpaceStatus(parts[3]);
        }

        return downRecordRespMessage;
    }

    private Object decodeSpaceStatusRespMessage(
            UboTerminalMessage tMsg,
            ChannelHandlerContext ctx) throws InvalidMessageException {

        String status = tMsg.getBody();

        SessionChannelHandler.Session session = ctx.pipeline().get(SessionChannelHandler.class).getSession();

        UboSpaceStatusMessage spaceStatusMessage = new UboSpaceStatusMessage();
        spaceStatusMessage.setMsgId(tMsg.getSerial());
        spaceStatusMessage.setType("SPACE-STATUS");
        spaceStatusMessage.setEqId(session.getEqId());
        spaceStatusMessage.setStatus(status);

        return spaceStatusMessage;
    }

    //BluetoothFollowMessage
    private Object decodeBluetoothFollowMessage(
            UboTerminalMessage tMsg,
            ChannelHandlerContext ctx) throws InvalidMessageException {

        String[] parts = tMsg.getBody().split(";");
        SessionChannelHandler.Session session = ctx.pipeline().get(SessionChannelHandler.class).getSession();

        BluetoothFollowMessage bluetoothFollowMessage = new BluetoothFollowMessage();
        bluetoothFollowMessage.setMsgId(tMsg.getSerial());
        bluetoothFollowMessage.setType("BLUETOOTH-FOLLOW");
        bluetoothFollowMessage.setEqId(session.getEqId());
        bluetoothFollowMessage.setFlag(parts[0]);

        return bluetoothFollowMessage;
    }

    @Override
    public Object decode(ChannelHandlerContext ctx, Channel channel,
                         Object object) throws Exception {

        UboTerminalMessage tMsg = (UboTerminalMessage) object;

        LOG.debug("IMEI: " + tMsg.getTid());
        LOG.debug("Serial: " + tMsg.getSerial());
        LOG.debug("Command: " + tMsg.getCmd());

        SessionChannelHandler.Session session = ctx.pipeline().get(SessionChannelHandler.class).getSession();

        Object msg = null;


        switch (Byte.parseByte(tMsg.getCmd())) {

            case 1: //login
            {
                msg = decodeLoginMessage(tMsg, ctx);
                break;
            }

            case 3: //gps-lbs

            {
                //gps-lbs message
                msg = decodeLocationMessage(tMsg, ctx);
                break;
            }

            case 4: //gps-lbs

            {
                //gps-lbs message
                msg = decodeResponsMessage(tMsg, ctx);
                break;
            }

            case 5: //login
            {
                msg = decodeResponMessage(tMsg, ctx);
                break;
            }
            case 7: //login
            {
                msg = decodeMarryMessage(tMsg, ctx);
                break;
            }
            case 11: //login
            {
                msg = decodeInWarehouseMessage(tMsg, ctx);
                break;
            }
            case 13:   // hb
            {
                msg = decodeHeartbeatMessage(tMsg, ctx);
                break;
            }

            case 15:  // sos alarm
            {
                msg = decodeSOSMessage(tMsg, ctx);
                break;
            }
            case 16: //gps-lbs
            {
                //gps-lbs message
                msg = decodeResponsMessage(tMsg, ctx);
                break;
            }
           /*
            case 0x05: //gps-lbs
            {
                //gps-lbs message
                msg = decodeGpsMessage(tMsg, ctx);
                break;
            }

            case 0x08: //gps-lbs
            {
                //gps-lbs message
                msg = decodeLocationMessage(tMsg, ctx);
                break;
            }

            case 10: //guardarea-ack
            {
                //guardarea ack message
            */
/*UboGuardAreaAckMessage guardAck = new UboGuardAreaAckMessage();
            guardAck.setMsgId(tMsg.getSerial());
			guardAck.setType("GUARDAREA-ACK");
			guardAck.setEqId(pipeline.getEqId());
			if (tMsg.getBody().equals("0")) {
				guardAck.setSuccess(true);
			} else {
				guardAck.setSuccess(false);
			}

			msg = guardAck;*//*

                msg = decodeGuardAckMessage(tMsg, ctx);
                break;
            }

            case 11: //guard-area-ind
            {
                //guard-area-ind message
                msg = decodeGuardMessage(tMsg, ctx);
                break;
            }

            case 37: //sleeptime-ack
            {
                //sleeptime ack message
                UboSleepTimeAckMessage sleepTimeAck = new UboSleepTimeAckMessage();
                sleepTimeAck.setMsgId(tMsg.getSerial());
                sleepTimeAck.setType("SLEEPTIME-ACK");
                sleepTimeAck.setEqId(session.getEqId());
                if (tMsg.getBody().equals("0")) {
                    sleepTimeAck.setSuccess(true);
                } else {
                    sleepTimeAck.setSuccess(false);
                }

                msg = sleepTimeAck;
                break;
            }



            case 50:  // sos alarm
            {
                msg = decodeSOSMessage(tMsg, ctx);
                break;
            }



            case 55: // comm resp
            {
                UboCommandRespMessage commandRespMessage = new UboCommandRespMessage();
                commandRespMessage.setMsgId(tMsg.getSerial());
                commandRespMessage.setType("COMM_RESP");
                commandRespMessage.setEqId(session.getEqId());
                commandRespMessage.setResult(tMsg.getBody());
                msg = commandRespMessage;
                break;
            }

            case 58:
                msg = decodeDownRecordRespMessage(tMsg, ctx);
                break;

            case 60:
                msg = decodeSpaceStatusRespMessage(tMsg, ctx);

                break;
            case 65://新增蓝牙随心接口
                msg = decodeBluetoothFollowMessage(tMsg, ctx);
                break;
*/

        }


        return msg;
    }


    @Override
    public Object decode(ChannelHandlerContext ctx, Channel channel, ByteBuf buffer)
            throws Exception {

        LOG.error("String data should not come here.");
        throw new Exception("Unimplemented decoder method.");

    }

    public static void main(String[] args) {

        BasicProtocolDecoder d = new BasicProtocolDecoder();

        String body = "W001;460015470013555;89860115851011843793;G6230VER002_20150525;3.860;67;14:c2:2d:90:70:a5;;5";
        String[] parts = body.split(";");
        for (int i = 0; i < parts.length; i++) {
            System.out.println("" + i + ":" + parts[i]);
        }

        if (parts[7] == null) {
            System.out.println("parts[7] is null");
        } else if (parts[7].equals("")) {
            System.out.println("parts[7] is empty");
        }

    }
}
