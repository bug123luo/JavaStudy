import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lcc
 * @date 2019/4/19 - 9:30
 */
public class DiffListSetMain {
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<String>();
        List<String> list2 = new ArrayList<String>();

        for(int i = 0; i < 10000; i++){
            list1.add("test" + i);
            list2.add("test" + i*2);
        }
        getDifferent(list1, list2);
        getDiffrent2(list1, list2);
        getDiffrent3(list1, list2);
        getDiffrent4(list1, list2);
        getDiffrent5(list1, list2);
    }

    private static List<String> getDifferent(List<String> list1, List<String> list2){
        long startTime = System.currentTimeMillis();
        List<String> diff = new ArrayList<String>();
        for(String str : list1){
            if(!list2.contains(str)){
                diff.add(str);
            }
        }
        System.out.println("Total Time: " + (System.currentTimeMillis() - startTime));
        return diff;
    }

    private static List<String> getDiffrent2(List<String> list1, List<String> list2) {
        long startTime = System.currentTimeMillis();
        list1.retainAll(list2);
        System.out.println("Total Time: " + (System.currentTimeMillis() - startTime));
        return list1;
    }

    private static List<String> getDiffrent3(List<String> list1, List<String> list2) {
        long startTime = System.currentTimeMillis();
        Map<String, Integer> map = new HashMap<String, Integer>(list1.size() + list2.size());
        List<String> diff = new ArrayList<String>();
        for (String string : list1) {
            map.put(string, 1);
        }
        for (String string : list2) {
            Integer cc = map.get(string);
            if (cc != null) {
                map.put(string, ++cc);
                continue;
            }
            map.put(string, 1);
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                diff.add(entry.getKey());
            }
        }
        System.out.println("Total Time: " + (System.currentTimeMillis() - startTime));
        return list1;
    }

    private static List<String> getDiffrent4(List<String> list1, List<String> list2) {
//        long st = System.nanoTime();
        long startTime = System.currentTimeMillis();

        Map<String,Integer> map = new HashMap<String,Integer>(list1.size()+list2.size());
        List<String> diff = new ArrayList<String>();
        List<String> maxList = list1;
        List<String> minList = list2;
        if(list2.size()>list1.size())
        {
            maxList = list2;
            minList = list1;
        }
        for (String string : maxList) {
            map.put(string, 1);
        }
        for (String string : minList) {
            Integer cc = map.get(string);
            if(cc!=null)
            {
                map.put(string, ++cc);
                continue;
            }
            map.put(string, 1);
        }
        for(Map.Entry<String, Integer> entry:map.entrySet())
        {
            if(entry.getValue()==1)
            {
                diff.add(entry.getKey());
            }
        }
//        System.out.println("getDiffrent4 total times "+(System.nanoTime()-st));
        System.out.println("Total Time: " + (System.currentTimeMillis() - startTime));

        return diff;

    }

    private static List<String> getDiffrent5(List<String> list1, List<String> list2) {
//        long st = System.nanoTime();
        long startTime = System.currentTimeMillis();

        List<String> diff = new ArrayList<String>();
        List<String> maxList = list1;
        List<String> minList = list2;
        if(list2.size()>list1.size())
        {
            maxList = list2;
            minList = list1;
        }
        Map<String,Integer> map = new HashMap<String,Integer>(maxList.size());
        for (String string : maxList) {
            map.put(string, 1);
        }
        for (String string : minList) {
            if(map.get(string)!=null)
            {
                map.put(string, 2);
                continue;
            }
            diff.add(string);
        }
        for(Map.Entry<String, Integer> entry:map.entrySet())
        {
            if(entry.getValue()==1)
            {
                diff.add(entry.getKey());
            }
        }
//        System.out.println("getDiffrent5 total times "+(System.nanoTime()-st));
        System.out.println("Total Time: " + (System.currentTimeMillis() - startTime));
        return diff;

    }
}
