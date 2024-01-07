package Project3.Ex2;

import Components.*;
import DataObjects.DataString;
import Enumerations.LogicConnector;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;

public class Monitor {
    public static void main(String[] args) {

        PetriNet pn = new PetriNet();
        pn.PetriNetName = "Main Petri";
        pn.NetworkPort = 1081;

        // monitor 1

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

        // monitor 2

        DataString p3 = new DataString();
        p3.SetValue("1");
        p3.SetName("P3");
        pn.PlaceList.add(p3);

        DataString p4 = new DataString();
        p4.SetName("P_2_Display");
        pn.PlaceList.add(p4);

        DataString p5 = new DataString();
        p5.SetName("in2");
        pn.PlaceList.add(p5);

        // monitor 3

        DataString p6 = new DataString();
        p6.SetValue("1");
        p6.SetName("P6");
        pn.PlaceList.add(p6);

        DataString p7 = new DataString();
        p7.SetName("P_3_Display");
        pn.PlaceList.add(p7);

        DataString p8 = new DataString();
        p8.SetName("in3");
        pn.PlaceList.add(p8);

        // monitor 4

        DataString p9 = new DataString();
        p9.SetValue("1");
        p9.SetName("P9");
        pn.PlaceList.add(p9);

        DataString p10 = new DataString();
        p10.SetName("P_4_Display");
        pn.PlaceList.add(p10);

        DataString p11 = new DataString();
        p11.SetName("in4");
        pn.PlaceList.add(p11);

        // transition t0
        PetriTransition t0 = new PetriTransition(pn);
        t0.TransitionName = "t0";
        t0.InputPlaceName.add("P0");
        t0.InputPlaceName.add("in");

        Condition T0Ct1 = new Condition(t0, "P0", TransitionCondition.NotNull);
        Condition T0Ct2 = new Condition(t0, "in", TransitionCondition.NotNull);
        T0Ct1.SetNextCondition(LogicConnector.AND, T0Ct2);

        GuardMapping grdT0 = new GuardMapping();
        grdT0.condition = T0Ct1;

        grdT0.Activations.add(new Activation(t0, "P0", TransitionOperation.Move, "P0"));
        grdT0.Activations.add(new Activation(t0, "in", TransitionOperation.Move, "P1"));
        t0.GuardMappingList.add(grdT0);

        pn.Transitions.add(t0);

        // transition t1
        PetriTransition t1 = new PetriTransition(pn);
        t1.TransitionName = "t1";
        t1.InputPlaceName.add("P3");
        t1.InputPlaceName.add("in2");

        Condition T1Ct1 = new Condition(t1, "P3", TransitionCondition.NotNull);
        Condition T1Ct2 = new Condition(t1, "in2", TransitionCondition.NotNull);
        T1Ct1.SetNextCondition(LogicConnector.AND, T1Ct2);

        GuardMapping grdT1 = new GuardMapping();
        grdT1.condition = T1Ct1;

        grdT1.Activations.add(new Activation(t1, "P3", TransitionOperation.Move, "P3"));
        grdT1.Activations.add(new Activation(t1, "in2", TransitionOperation.Move, "P4"));
        t0.GuardMappingList.add(grdT1);

        pn.Transitions.add(t1);

        // transition t2
        PetriTransition t2 = new PetriTransition(pn);
        t2.TransitionName = "t2";
        t2.InputPlaceName.add("P6");
        t2.InputPlaceName.add("in3");

        Condition T2Ct1 = new Condition(t2, "P6", TransitionCondition.NotNull);
        Condition T2Ct2 = new Condition(t2, "in3", TransitionCondition.NotNull);
        T2Ct1.SetNextCondition(LogicConnector.AND, T2Ct2);

        GuardMapping grdT2 = new GuardMapping();
        grdT2.condition = T1Ct1;

        grdT2.Activations.add(new Activation(t2, "P6", TransitionOperation.Move, "P6"));
        grdT2.Activations.add(new Activation(t2, "in3", TransitionOperation.Move, "P7"));
        t2.GuardMappingList.add(grdT2);

        pn.Transitions.add(t2);

        // transition t3
        PetriTransition t3 = new PetriTransition(pn);
        t3.TransitionName = "t3";
        t3.InputPlaceName.add("P9");
        t3.InputPlaceName.add("in4");

        Condition T3Ct1 = new Condition(t3, "P9", TransitionCondition.NotNull);
        Condition T3Ct2 = new Condition(t3, "in4", TransitionCondition.NotNull);
        T3Ct1.SetNextCondition(LogicConnector.AND, T3Ct2);

        GuardMapping grdT3 = new GuardMapping();
        grdT3.condition = T1Ct1;

        grdT3.Activations.add(new Activation(t3, "P9", TransitionOperation.Move, "P9"));
        grdT3.Activations.add(new Activation(t3, "in4", TransitionOperation.Move, "P10"));
        t3.GuardMappingList.add(grdT3);

        pn.Transitions.add(t3);

        PetriNetWindow frame = new PetriNetWindow(false);
        frame.petriNet = pn;
        frame.setVisible(true);
    }

}
