package Tasks.week1;

import Components.*;
import DataObjects.DataFloat;
import DataObjects.DataTransfer;
import DataOnly.TransferOperation;
import Enumerations.LogicConnector;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;
import GUIs.InputFloat;

public class Client {
    public static void main(String[] args) {
        // --- Initialize Petri Net ---
        PetriNet clientNet = new PetriNet();
        clientNet.PetriNetName = "Client";
        clientNet.NetworkPort = 1080;

        // --- Places ---
        DataFloat p0 = new DataFloat();
        p0.SetName("P0");
        p0.SetValue(1.0f);
        clientNet.PlaceList.add(p0);

        DataFloat p1 = new DataFloat();
        p1.SetName("P1");
        p1.SetValue(0.0f);
        clientNet.PlaceList.add(p1);

        DataTransfer p3 = new DataTransfer();
        p3.SetName("P3");
        clientNet.PlaceList.add(p3);
        p3.Value = new TransferOperation("localhost", "1081", "P1");

        DataFloat p4 = new DataFloat();
        p4.SetName("P4");
        clientNet.PlaceList.add(p4);

        DataFloat p5 = new DataFloat();
        p5.SetName("P5");
        clientNet.PlaceList.add(p5);

        DataFloat p6 = new DataFloat();
        p6.SetName("P6");
        clientNet.PlaceList.add(p6);

        // --- Transitions ---
        // Transition T1
        PetriTransition t1 = new PetriTransition(clientNet);
        t1.TransitionName = "T1";
        t1.InputPlaceName.add("P0");
        t1.InputPlaceName.add("P1");

        Condition T1Ct1 = new Condition(t1, "P0", TransitionCondition.NotNull);
        Condition T1Ct2 = new Condition(t1, "P1", TransitionCondition.NotNull);
        T1Ct1.SetNextCondition(LogicConnector.AND, T1Ct2);

        GuardMapping grdT1 = new GuardMapping();
        grdT1.condition = T1Ct1;
        if (p1.GetValue() != null) {
            grdT1.Activations.add(new Activation(t1, "P1", TransitionOperation.Move, "P3"));
        }
        if (p0.GetValue() != null) {
            grdT1.Activations.add(new Activation(t1, "P0", TransitionOperation.Move, "P4"));
        }
        grdT1.Activations.add(new Activation(t1, "P3", TransitionOperation.SendOverNetwork, "P1"));
        t1.GuardMappingList.add(grdT1);

        t1.Delay = 0;
        clientNet.Transitions.add(t1);

        // Transition T2
        PetriTransition t2 = new PetriTransition(clientNet);
        t2.TransitionName = "T2";
        t2.InputPlaceName.add("P4");
        t2.InputPlaceName.add("P5");

        Condition T2Ct1 = new Condition(t2, "P4", TransitionCondition.NotNull);
        Condition T2Ct2 = new Condition(t2, "P5", TransitionCondition.NotNull);
        T2Ct1.SetNextCondition(LogicConnector.AND, T2Ct2);

        GuardMapping grdT2 = new GuardMapping();
        grdT2.condition = T2Ct1;
        grdT2.Activations.add(new Activation(t2, "P5", TransitionOperation.Move, "P6"));
        grdT2.Activations.add(new Activation(t2, "P4", TransitionOperation.Move, "P4"));
        t2.GuardMappingList.add(grdT2);

        t2.Delay = 0;
        clientNet.Transitions.add(t2);

        // --- Display Petri Net ---
        PetriNetWindow frame = new PetriNetWindow(false);
        frame.petriNet = clientNet;
        frame.setVisible(true);

        InputFloat.main(args);
        Server.main(args);
    }
}