package com.github.bryanww.samplebot;

// Open source sc2java wrapper
// The goal is to reduce the verbosity provided by the current iteration of sc2java
// The sample bot access the raw interface, but we can reduce the complexity and keep the syntax simple as well.
public class PlayerInterface {
	// Variables used by main agent
	public int minerals;
	public int gas;
	public int supplyUsed;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	// On step update the player variables
	// This may be unnecessary?
	// r.getObservation().getPlayerCommon().getMinerals() <-- reduce this to --> Player.minerals
	
	//Bot developers should implement their bot in this function instead of that lambda function provided by the raw interface.
	public void on_step()
	{
		
	}
	
	// The sample bot hard codes in the cost of each unit, and uses very wordy methods to train a unit.
	// TODO: look into how units are trained and how buildings are build.
	// The arguments should take in necessary variables and call the client to carry out the action without a huge chain of methods
	// e.g, can we make this a one line method call?
	/*
    if (r.getObservation().getPlayerCommon().getMinerals() - mineralsSpentThisStep >= 50
            && r.getObservation().getPlayerCommon().getFoodUsed() < (r.getObservation().getPlayerCommon().getFoodCap() - 1)) {
        client.request(actions().of(
                action().raw(ActionRawUnitCommand.unitCommand().forUnits(u.getTag()).useAbility(TRAIN_DRONE))
        ));
        mineralsSpentThisStep += 50;
    }

	  
	 */
	public void TrainUnit()
	{
		
	}
}
