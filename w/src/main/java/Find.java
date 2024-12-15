import org.apache.taglibs.standard.tei.ForEachTEI;

public class Find {
    public static void main(String[] args) {
        System.out.println(
        		ForEachTEI.class.getProtectionDomain().getCodeSource().getLocation());
    }
}