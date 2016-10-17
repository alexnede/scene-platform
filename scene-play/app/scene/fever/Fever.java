package scene.fever;

import br.ufes.inf.lprm.situation.Part;
import br.ufes.inf.lprm.situation.SituationType;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

//@Publish(host="host", port=4040)
public class Fever extends SituationType {
	
	@Part(label = "f1")
	private Person febrile;

	public void setFebrile(Person febrile) {
		this.febrile = febrile;
	}

	public Person getFebrile() {
		return febrile;
	}
	@Override
	public void setActive() {
		super.setActive();
		System.out.println(febrile.getName() + ": Fever activated at " + new SimpleDateFormat("H:mm:ss").format(  new Date( this.getActivation().getTimestamp() ) ) );		
	}
	@Override
	public void setInactive() {
		super.setInactive();
		System.out.println(febrile.getName() + ": Fever deactivated at " + new SimpleDateFormat("H:mm:ss").format(  new Date( this.getDeactivation().getTimestamp() ) )  + ". It lasted: " + TimeUnit.MILLISECONDS.toSeconds( (this.getDeactivation().getTimestamp() - this.getActivation().getTimestamp()) ) + " seconds" );		
	}	
}
