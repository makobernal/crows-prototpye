package controllers;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {

	private static final String NEO4J_DB_PATH = "/home/virtualcrow/stack/neo4j/data/graph3.db";

	private static enum RelTypes implements RelationshipType {
		NextEvent, PreviousEvent
	}

	public static Result index() {
		return ok(index.render("Your new application is ready."));
	}

	public static Result cypher() {
		GraphDatabaseService graphDb = new GraphDatabaseFactory()
				.newEmbeddedDatabase(NEO4J_DB_PATH);
		registerShutdownHook(graphDb);

		Transaction tx = graphDb.beginTx();
		try {
			Node firstEvent = graphDb.createNode();
			firstEvent.setProperty("id", "12345");
			firstEvent.setProperty("type", "testnode");

			Node secondEvent = graphDb.createNode();
			secondEvent.setProperty("id", "12345678");

			Relationship goRelationship = firstEvent.createRelationshipTo(
					secondEvent, RelTypes.NextEvent);

			tx.success();
		} finally {
			tx.finish();
		}

		graphDb.shutdown();

		return ok(index.render("Cypher stuff has been happening!!!"));
	}

	private static void registerShutdownHook(final GraphDatabaseService graphDb) {
		// Registers a shutdown hook for the Neo4j instance so that it
		// shuts down nicely when the VM exits (even if you "Ctrl-C" the
		// running example before it's completed)
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				graphDb.shutdown();
			}
		});
	}

}
