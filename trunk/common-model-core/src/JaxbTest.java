import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.Unmarshaller.Listener;
import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamReader;

import com.butone.codeinf.model.CodeDef;
import com.butone.codeinf.model.node.ConstantNode;
import com.butone.codeinf.model.node.DateNode;
import com.butone.codeinf.model.node.ParameterNode;
import com.butone.codeinf.model.node.SequenceNode;
import com.butone.utils.StringUtils;
import com.butone.xml.JaxbUtils;

public class JaxbTest {

	private static void codeDefTest() {
		CodeDef def = new CodeDef();
		def.setGuid("123");
		def.setName("测试");
		ConstantNode n0 = new ConstantNode();
		n0.setGuid(StringUtils.getNewGuid32());
		n0.setConstStr("测试");
		n0.setRelateSequenceValue(true);
		def.getNodes().add(n0);

		ParameterNode p0 = new ParameterNode();
		p0.setGuid("p1");
		p0.setRelateSequenceValue(true);
		def.getNodes().add(p0);

		DateNode n1 = new DateNode();
		n1.setGuid(StringUtils.getNewGuid32());
		n1.setUseYear(true);
		n1.setRelateSequenceValue(true);
		def.getNodes().add(n1);
		SequenceNode n2 = new SequenceNode();
		def.getNodes().add(n2);
		try {
			System.out.println(new String(JaxbUtils.marshal(def, "utf-8"),
					"utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] a) {
		// testMaterialGroup();
		// genWidnow();
		// windowTempletTest();
		codeDefTest();
	}

	static class LocationListener extends Listener {

		private XMLStreamReader xsr;
		private Map<Object, Location> locations;

		public LocationListener(XMLStreamReader xsr) {
			this.xsr = xsr;
			this.locations = new HashMap<Object, Location>();
		}

		@Override
		public void beforeUnmarshal(Object target, Object parent) {
			locations.put(target, xsr.getLocation());
		}

		public Location getLocation(Object o) {
			return locations.get(o);
		}

	}

}
