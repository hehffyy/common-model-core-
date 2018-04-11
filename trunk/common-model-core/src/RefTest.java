import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RefTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		System.out.println("/BIZ/ml150303/yw142059/process/LC223527/LC223527Process/act1/before/ada=0"
//				.substring("/BIZ/ml150303/yw142059/process/LC223527/LC223527Process".length() + 1));
//
//		System.out.println("act1/before/ada=0".substring(0, "act1/before/ada=0".indexOf("=")));

		String sql = "select * from abc where f1 = :p_1 and f2=:p-2 and f3 =:p1\n and f4=:123 ";
		String regE = "(:[a-zA-Z0-9_]*)";//\\
		Pattern p = Pattern.compile(regE);
		
		Matcher m = p.matcher(sql);
		
		int i=0;
		while(m.find()){
			System.out.println((i++) + "=" + m.group());
		}
		
		
//		Class<?> c = Biz.class;
//		if (c.getAnnotations() != null)
//			for (Annotation a : c.getAnnotations()) {
//
//				System.out.println(a.annotationType().getName());
//			}
//		for (Field fld : c.getDeclaredFields()) {
//			if (fld.getAnnotation(javax.xml.bind.annotation.XmlTransient.class) == null && !Modifier.isStatic(fld.getModifiers()))
//				System.out.println(fld.getName() + ":" + fld.getType().getName());
//		}
//
//		for (Method m : c.getMethods()) {
//			if (!Modifier.isPublic(m.getModifiers()) || (m.getName().startsWith("get") || m.getName().startsWith("is"))) {
//				continue;
//			}
//			System.out.println(m.getName() + " () " + m.getReturnType().getName());
//			for (Annotation a : m.getAnnotations()) {
//				System.out.println(a.annotationType().getName());
//			}
//
//		}

	}
}
