import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Text;
import org.jdom.output.XMLOutputter;
import junit.framework.TestCase;

/**
 * @author VISTALL
 * @since 17:17/11.05.13
 */
public class IoTest extends TestCase
{
	public void testElement() throws Exception
	{
		Document document = new Document();
		document.addContent(new Element("element"));

		test(document);
	}

	public void testAttributeAndElement() throws Exception
	{
		Document document = new Document();
		final Element element = new Element("element");
		element.setAttribute("key", "value");
		document.addContent(element);

		test(document);
	}

	public void testElementWithChild() throws Exception
	{
		Document document = new Document();
		final Element element = new Element("element");
		element.setAttribute("key", "value");
		element.addContent(new Element("child-element"));
		element.addContent(new Text("inner text"));

		document.addContent(element);

		test(document);
	}

	private void test(Document content) throws Exception
	{
		StringWriter stringWriter = new StringWriter();

		new XMLOutputter().output(content, stringWriter);

		String testName = getName();
		testName = testName.substring(4, testName.length());

		Reader reader = new InputStreamReader(getClass().getResourceAsStream("/" + testName + ".xml"), StandardCharsets.UTF_8);
		StringBuilder builder = new StringBuilder();
		int i = -1;
		while((i = reader.read()) != -1)
		{
			builder.append((char) i);
		}

		assertEquals(builder.toString(), stringWriter.toString());
	}
}
