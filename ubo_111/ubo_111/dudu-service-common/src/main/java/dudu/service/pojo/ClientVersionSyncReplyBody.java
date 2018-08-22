package dudu.service.pojo;

import lombok.Data;

@Data
public class ClientVersionSyncReplyBody {
	private String reserve;
	private String lastVersion;
	private String downloadUrl;
	private String username;
	private String command;
}
