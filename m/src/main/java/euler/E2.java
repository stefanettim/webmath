package euler;

public class E2 {
	
	public static long LIMIT=4000000;
	
	public static void main(String[] p) {
		long f1=1;
		long f1c=f1;
		long f=2;
		long s=2;
		
		while(f<LIMIT) {
			f1=f;
			f+=f1c;
			f1c=f1;
			System.out.println("f:"+f);
			
			if(f%2==0) {
				s+=f;
			}
		}
		
		System.out.println("tot:"+s);
	}

}
