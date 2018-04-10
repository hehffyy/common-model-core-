package com.butone.xml;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;

import com.butone.model.config.LogicFieldType;
import com.sun.xml.bind.marshaller.CharacterEscapeHandler;
import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

public class JaxbUtils {

	public static void main(String[] args) {
		generateSchema(LogicFieldType.class);
	}

	static class MySchemaOutputResolver extends SchemaOutputResolver {

		private File f;

		public MySchemaOutputResolver(String dir, String fileName) {
			f = new File(dir, fileName);
		}

		@Override
		public Result createOutput(String namespaceUri, String suggestedFileName)
				throws IOException {
			return new StreamResult(f);
		}
	}

	private static void generateSchema(Class<?>... classes) {
		try {
			JAXBContext context = JAXBContext.newInstance(classes);
			context.generateSchema(new MySchemaOutputResolver("c:\\", "123.xsd"));
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static Marshaller createMarshaller(String encoding,
			final Map<String, String> prefixMapper,
			Class<?>... classesToBeBound) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(classesToBeBound);
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
		marshaller.setProperty(
				"com.sun.xml.bind.marshaller.CharacterEscapeHandler",
				new CharacterEscapeHandler() {
					@Override
					public void escape(char[] ch, int start, int length,
							boolean isAttVal, Writer writer) throws IOException {
						writer.write(ch, start, length);

					}
				});
		if (prefixMapper != null)
			marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper",
					new NamespacePrefixMapper() {
						@Override
						public String getPreferredPrefix(String namespaceUri,
								String suggestion, boolean requirePrefix) {
							String r = prefixMapper.get(namespaceUri);
							if (requirePrefix) {
								if (requirePrefix && r != null) {
									return r;
								}
								return suggestion;
							}
							return null;

						}
					});
		return marshaller;
	}

	public static void marshal(Object o, String encoding, Result result,
			Class<?>... classesToBeBound) throws JAXBException {
		if (classesToBeBound == null || classesToBeBound.length == 0) {
			createMarshaller(encoding, null, o.getClass()).marshal(o, result);
		} else {
			createMarshaller(encoding, null, classesToBeBound).marshal(o,
					result);
		}
	}

	// public static byte[] marshal(Object o, String encoding,
	// final Map<String, String> prefixMapper,
	// Class<?>... classesToBeBound) throws JAXBException {
	// JAXBContext jaxbContext = JAXBContext.newInstance(classesToBeBound);
	// ByteArrayOutputStream out = new ByteArrayOutputStream();
	// Marshaller marshaller = jaxbContext.createMarshaller();
	// marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	// marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
	// marshaller.setProperty(
	// "com.sun.xml.bind.marshaller.CharacterEscapeHandler",
	// new CharacterEscapeHandler() {
	// @Override
	// public void escape(char[] ch, int start, int length,
	// boolean isAttVal, Writer writer) throws IOException {
	// writer.write(ch, start, length);
	// }
	// });
	// if (prefixMapper != null)
	// marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper",
	// new NamespacePrefixMapper() {
	// @Override
	// public String getPreferredPrefix(String namespaceUri,
	// String suggestion, boolean requirePrefix) {
	// String r = prefixMapper.get(namespaceUri);
	// if (requirePrefix) {
	// if (requirePrefix && r != null) {
	// return r;
	// }
	// return suggestion;
	// }
	// return null;
	//
	// }
	// });
	// marshaller.marshal(o, out);
	// return out.toByteArray();
	// }

	public static byte[] marshal(Object o, String encoding,
			final Map<String, String> prefixMapper) throws JAXBException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		createMarshaller(encoding, prefixMapper, o.getClass()).marshal(o, out);
		return out.toByteArray();
	}

	public static byte[] marshal(Object o, String encoding)
			throws JAXBException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		createMarshaller(encoding, null, o.getClass()).marshal(o, out);
		return out.toByteArray();
	}

	public static byte[] marshal(Object o, String encoding,
			Class<?>... classesToBeBound) throws JAXBException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		createMarshaller(encoding, null, classesToBeBound).marshal(o, out);
		return out.toByteArray();
	}

	public static Object unMarshal(InputStream in, String encoding,
			Class<?>... classesToBeBound) throws Exception {
		JAXBContext jaxbContext = JAXBContext.newInstance(classesToBeBound);
		Unmarshaller unJaxbMarshaller = jaxbContext.createUnmarshaller();
		// XMLInputFactory xif = XMLInputFactory.newInstance();
		// XMLStreamReader xsr = xif.createXMLStreamReader(in);
		// InputSource is = new InputSource(in);
		// is.setEncoding(encoding);
		Reader read = new InputStreamReader(in, encoding);
		return unJaxbMarshaller.unmarshal(read);
		// return unJaxbMarshaller.unmarshal(in);
	}

	public static Object unMarshal(Source in, Class<?>... classesToBeBound)
			throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(classesToBeBound);
		Unmarshaller unJaxbMarshaller = jaxbContext.createUnmarshaller();
		// XMLInputFactory xif = XMLInputFactory.newInstance();
		// XMLStreamReader xsr = xif.createXMLStreamReader(in);
		return unJaxbMarshaller.unmarshal(in);
	}

	public static Object clone(Object o, Class<?>... classesToBeBound)
			throws Exception {
		JAXBContext jaxbContext = JAXBContext.newInstance(classesToBeBound);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "utf-8");
		jaxbMarshaller.marshal(o, out);
		ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
		return unMarshal(in, "utf-8", classesToBeBound);
	}

}
