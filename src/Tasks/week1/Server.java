package Tasks.week1;

import java.util.ArrayList;

import Components.*;
import DataObjects.DataFloat;
import DataObjects.DataTransfer;
import DataOnly.TransferOperation;
import Enumerations.LogicConnector;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;

public class Server {
    public static void main(String[] args) {
        // --- Initialize Petri Net ---
        PetriNet serverNet = new PetriNet();
        serverNet.PetriNetName = "Server";
        serverNet.NetworkPort = 1081;

        // --- Places ---
        DataFloat p0 = new DataFloat();
        p0.SetName("P0");
        p0.SetValue(1.0f);
        serverNet.PlaceList.add(p0);

        DataFloat p1 = new DataFloat();
        p1.SetName("P1");
        serverNet.PlaceList.add(p1);

        DataFloat p2 = new DataFloat();
        p2.SetName("P2");
        serverNet.PlaceList.add(p2);

        DataTransfer p3 = new DataTransfer();
        p3.SetName("P3");
        serverNet.PlaceList.add(p3);
        p3.Value = new TransferOperation("localhost", "1080", "P1");

        DataFloat subConstantValue1 = new DataFloat();
        subConstantValue1.SetName("subConstantValue1");
        subConstantValue1.SetValue(0.01f);
        spn.ConstantPlaceList.add(subConstantValue1);

        // --- Transitions ---
        // Transition T1
        PetriTransition t1 = new PetriTransition(serverNet);
        t1.TransitionName = "T1";
        t1.InputPlaceName.add("P0");
        t1.InputPlaceName.add("P1");

        Condition T1Ct1 = new Condition(t1, "P0", TransitionCondition.NotNull);
        Condition T1Ct2 = new Condition(t1, "P1", TransitionCondition.NotNull);
        T1Ct1.SetNextCondition(LogicConnector.AND, T1Ct2);

        ArrayList<String> lstInput = new ArrayList<String>();
        lstInput.add("P1");
        lstInput.add("subConstantValue1");

        GuardMapping grdT1 = new GuardMapping();
        grdT1.condition = T1Ct1;
        grdT1.Activations.add(new Activation(t1, lstInput, TransitionOperation.Prod, "P2"));
        // grdT1.Activations.add(new Activation(t1, "P0", TransitionOperation.Move, "P2"));
        t1.GuardMappingList.add(grdT1);

        t1.Delay = 0;
        serverNet.Transitions.add(t1);

        // Transition T2
        PetriTransition t2 = new PetriTransition(serverNet);
        t2.TransitionName = "T2";
        t2.InputPlaceName.add("P2");

        Condition T2Ct1 = new Condition(t2, "P2", TransitionCondition.NotNull);

        GuardMapping grdT2 = new GuardMapping();
        grdT2.condition = T2Ct1;
        grdT2.Activations.add(new Activation(t2, "P2", TransitionOperation.SendOverNetwork, "P3"));
        grdT2.Activations.add(new Activation(t2, "P2", TransitionOperation.Move, "P0"));
        t2.GuardMappingList.add(grdT2);

        t2.Delay = 0;
        serverNet.Transitions.add(t2);

        PetriNetWindow frame = new PetriNetWindow(false);
        frame.petriNet = serverNet;
        frame.setVisible(true);
    }

}