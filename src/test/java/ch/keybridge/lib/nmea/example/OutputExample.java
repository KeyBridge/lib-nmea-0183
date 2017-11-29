package ch.keybridge.lib.nmea.example;

import org.nmea.parser.SentenceFactory;
import org.nmea.sentence.MWVSentence;
import org.nmea.sentence.TalkerId;
import org.nmea.util.DataStatus;
import org.nmea.util.Units;

/**
 * Demonstrates the usage of sentence parsers for data output.
 * 
 * @author Kimmo Tuukkanen
 */
public class OutputExample {

	public static void main(String[] args) {
		
		// Create a fresh MWV parser
		SentenceFactory sf = SentenceFactory.getInstance();
		MWVSentence mwv = (MWVSentence) sf.createParser(TalkerId.II, "MWV");
		
		// should output "$IIMWV,,,,,V*36" 
		System.out.println(mwv.toSentence());
		
		// Be sure to set all needed values correctly. For instance, in this
		// example setAngle() and setTrue() have mutual dependency. Likewise,
		// pay attention to set units correctly.
		mwv.setAngle(43.7);
		mwv.setTrue(true);
		mwv.setSpeed(4.54);
		mwv.setSpeedUnit(Units.METER);
		mwv.setStatus(DataStatus.ACTIVE);
		
		// should output "$IIMWV,043.7,T,4.5,M,A*39"
		System.out.println(mwv.toSentence());
	}

}
