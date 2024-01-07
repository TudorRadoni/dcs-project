package Project3.Ex1;

import Components.Activation;
import Components.Condition;
import Components.GuardMapping;
import Components.PetriNet;
import Components.PetriNetWindow;
import Components.PetriTransition;
import DataObjects.DataString;
import Enumerations.LogicConnector;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;

public class Monitor {
  public static void main(String[] args) {

    PetriNet pn = new PetriNet();
    pn.PetriNetName = "Main Petri";
    pn.NetworkPort = 1081;

    DataString p0 = new DataString();
    p0.SetValue("1");
    p0.SetName("P0");
    pn.PlaceList.add(p0);

    DataString p1 = new DataString();
    p1.SetName("P_1_Display");
    pn.PlaceList.add(p1);

    DataString p2 = new DataString();
    p2.SetName("in");
    pn.PlaceList.add(p2);

    // transition t0
    PetriTransition t0 = new PetriTransition(pn);
    t0.TransitionName = "t0";
    t0.InputPlaceName.add("P0");
    t0.InputPlaceName.add("in");

    Condition T1Ct1 = new Condition(t0, "P0", TransitionCondition.NotNull);
    Condition T1Ct2 = new Condition(t0, "in", TransitionCondition.NotNull);
    T1Ct1.SetNextCondition(LogicConnector.AND, T1Ct2);

    GuardMapping grdT1 = new GuardMapping();
    grdT1.condition = T1Ct1;

    grdT1.Activations.add(new Activation(t0, "P0", TransitionOperation.Move, "P0"));
    grdT1.Activations.add(new Activation(t0, "in", TransitionOperation.Move, "P1"));
    t0.GuardMappingList.add(grdT1);

    pn.Transitions.add(t0);

    PetriNetWindow frame = new PetriNetWindow(false);
    frame.petriNet = pn;
    frame.setVisible(true);
  }

}