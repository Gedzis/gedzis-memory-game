package net.gedzis.memory.handler;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import net.gedzis.memory.common.Constants;
import net.gedzis.memory.model.PlayerScore;

import org.xmlpull.v1.XmlSerializer;

import android.content.Context;
import android.util.Log;
import android.util.Xml;

public class LocalHighScoreOutputHandler {
	private XmlSerializer serializer;

	// http://www.anddev.org/write_a_simple_xml_file_in_the_sd_card_using_xmlserializer-t8350.html

	public void saveScore(List<PlayerScore> highScoreList, Context context) {
		// we have to bind the new file with a FileOutputStream
		FileOutputStream fileos = null;
		try {
			fileos = context.openFileOutput(
					Constants.LOCAL_HIGH_SCORE_FILENAME, Context.MODE_PRIVATE);
		} catch (FileNotFoundException e) {
			Log.e("FileNotFoundException", "can't create FileOutputStream");
		}
		// we create a XmlSerializer in order to write xml data
		serializer = Xml.newSerializer();
		try {
			// we set the FileOutputStream as output for the serializer, using
			// UTF-8 encoding
			serializer.setOutput(fileos, "UTF-8");
			// Write <?xml declaration with encoding (if encoding not null) and
			// standalone flag (if standalone not null)
			serializer.startDocument(null, Boolean.valueOf(true));
			// set indentation option
			serializer.setFeature(
					"http://xmlpull.org/v1/doc/features.html#indent-output",
					true);
			// start a tag called "root"
			serializer.startTag(null, Constants.LOCAL_HIGH_SCORE_TAG);
			// Table tag
			String tableStyle = "2x2";
			serializer.startTag(null, Constants.LOCAL_HIGH_SCORE_TABLE_TAG);
			serializer.attribute(null,
					Constants.LOCAL_HIGH_SCORE_TABLE_TYPE_TAG, tableStyle);
			writePlayerScore(highScoreList.get(0));
			serializer.endTag(null, Constants.LOCAL_HIGH_SCORE_TABLE_TAG);

			serializer.endTag(null, Constants.LOCAL_HIGH_SCORE_TAG);
			serializer.endDocument();
			// write xml data into the FileOutputStream
			serializer.flush();
			// finally we close the file stream
			System.out.println("AAAAAAAA" + fileos.toString());
			fileos.close();
		} catch (Exception e) {
			Log.e("Exception", "error occurred while creating xml file");
		}

	}

	private void writePlayerScore(PlayerScore playerScore)
			throws IllegalArgumentException, IllegalStateException, IOException {
		serializer.startTag(null, Constants.LOCAL_HIGH_SCORE_PLAYER_TAG);

		// Player Name
		serializer.startTag(null, Constants.LOCAL_HIGH_SCORE_PLAYER_NAME_TAG);
		serializer.text(playerScore.getName());
		serializer.endTag(null, Constants.LOCAL_HIGH_SCORE_PLAYER_NAME_TAG);

		// Player Turns
		serializer.startTag(null, Constants.LOCAL_HIGH_SCORE_PLAYER_TURNS_TAG);
		serializer.text(Integer.toString(playerScore.getTurns()));
		serializer.endTag(null, Constants.LOCAL_HIGH_SCORE_PLAYER_TURNS_TAG);

		// Player Name
		serializer.startTag(null, Constants.LOCAL_HIGH_SCORE_PLAYER_TIME_TAG);
		serializer.text(Integer.toString(playerScore.getTime()));
		serializer.endTag(null, Constants.LOCAL_HIGH_SCORE_PLAYER_TIME_TAG);

		serializer.endTag(null, Constants.LOCAL_HIGH_SCORE_PLAYER_TAG);

	}
}
