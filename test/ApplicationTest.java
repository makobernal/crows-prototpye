import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.contentType;

import org.junit.Assert;
import org.junit.Test;

import play.mvc.Content;
import play.mvc.Result;
import controllers.Application;

/**
 * 
 * Simple (JUnit) tests that can call all parts of a play app. If you are
 * interested in mocking a whole application, see the wiki for more details.
 * 
 */
public class ApplicationTest {

	@Test
	public void simpleCheck() {
		int a = 1 + 1;
		assertThat(a).isEqualTo(2);
	}

	@Test
	public void renderTemplate() {
		Content html = views.html.index
				.render("Your new application is ready.");
		assertThat(contentType(html)).isEqualTo("text/html");
		assertThat(contentAsString(html)).contains(
				"Your new application is ready.");
	}

	@Test
	public void stupidTest() {
		Result culo = Application.cypher();
		Assert.assertNotNull(culo);
	}
	
	@Test
	public void tinkerTest() {
		Graph graph = new Neo4jGraph("/tmp/my_graph");
		Vertex a = graph.addVertex(null);
		Vertex b = graph.addVertex(null);
		a.setProperty("name","marko");
		b.setProperty("name","peter");
		Edge e = graph.addEdge(null, a, b, "knows");
		e.setProperty("since", 2006);
		graph.shutdown();
	}

}
