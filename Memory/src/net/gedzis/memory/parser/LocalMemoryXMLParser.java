package net.gedzis.memory.parser;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import net.gedzis.memory.common.Constants;
import net.gedzis.memory.model.PlayerScore;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class LocalMemoryXMLParser {
	public List<PlayerScore> getLocalHighScoreRecords() {
		return parseFile();
	}

	public List<PlayerScore> parseFile() {
		List<PlayerScore> playerScores = new ArrayList<PlayerScore>();
		// http://www.java-tips.org/java-se-tips/javax.xml.parsers/how-to-read-xml-file-in-java.html
		try {
			URL sourceUrl = new URL("http://gedzis.net/database.xml");
			// File file = new File("c:\\MyXMLFile.xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new InputSource(sourceUrl.openStream()));
			doc.getDocumentElement().normalize();
			NodeList tableNodes = doc
					.getElementsByTagName(Constants.LOCAL_HIGH_SCORE_TABLE_TAG);
			System.out.println("Information of all employees "
					+ tableNodes.getLength());
			for (int s = 0; s < tableNodes.getLength(); s++) {

				Node tableNode = tableNodes.item(s);
				if (tableNode.getNodeType() == Node.ELEMENT_NODE) {
					Element tableElement = (Element) tableNode;
					NodeList playerNodes = tableElement
							.getElementsByTagName(Constants.LOCAL_HIGH_SCORE_PLAYER_TAG);
					for (int i = 0; i < playerNodes.getLength(); i++) {
						PlayerScore playerScore = new PlayerScore();
						playerScore
								.setTable(tableNode
										.getAttributes()
										.getNamedItem(
												Constants.LOCAL_HIGH_SCORE_TABLE_TYPE_TAG)
										.getNodeValue());
						Node playerNode = playerNodes.item(i);
						if (playerNode.getNodeType() == Node.ELEMENT_NODE) {
							Element playerElement = (Element) playerNode;
							playerScore
									.setName(getPlayerNodeValue(
											playerElement,
											Constants.LOCAL_HIGH_SCORE_PLAYER_NAME_TAG));
							playerScore
									.setTime(Integer
											.parseInt(getPlayerNodeValue(
													playerElement,
													Constants.LOCAL_HIGH_SCORE_PLAYER_TIME_TAG)));
							playerScore
									.setTurns(Integer
											.parseInt(getPlayerNodeValue(
													playerElement,
													Constants.LOCAL_HIGH_SCORE_PLAYER_TURNS_TAG)));
						}
						playerScores.add(playerScore);
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return playerScores;
	}

	private String getPlayerNodeValue(Element element, String tag) {
		NodeList nodeList = element.getElementsByTagName(tag);
		Element firstElement = (Element) nodeList.item(0);
		NodeList firstElementNodeList = firstElement.getChildNodes();
		System.out.println(tag + " "
				+ ((Node) firstElementNodeList.item(0)).getNodeValue());
		return ((Node) firstElementNodeList.item(0)).getNodeValue();
	}
}
