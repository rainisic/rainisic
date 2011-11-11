// ElevatorCaseStudy.java
// Application with Elevator Model, View, and Controller (MVC)
package com.deitel.jhtp5.elevator;

// Java core packages
import java.awt.*;

// Java extension packages
import javax.swing.*;

// Deitel packages
import com.deitel.jhtp5.elevator.model.*;
import com.deitel.jhtp5.elevator.view.*;
import com.deitel.jhtp5.elevator.controller.*;

public class ElevatorCaseStudy extends JFrame {

   // model, view and controller
   private ElevatorSimulation model;
   private ElevatorView view;
   private ElevatorController controller;

   // constructor instantiates model, view, and controller
   public ElevatorCaseStudy()
   {
      super( "Deitel Elevator Simulation" );

      // instantiate model, view and ,controller
      model = new ElevatorSimulation();
      view = new ElevatorView();
      controller = new ElevatorController( model );

      // register View for Model events
      model.setElevatorSimulationListener( view );

      // add view and controller to ElevatorSimulation
      getContentPane().add( view, BorderLayout.CENTER );
      getContentPane().add( controller, BorderLayout.SOUTH );

   } // end ElevatorSimulation constructor

   // main method starts program
   public static void main( String args[] )
   {
      // instantiate ElevatorSimulation
      ElevatorCaseStudy caseStudy = new ElevatorCaseStudy();
      caseStudy.setDefaultCloseOperation( EXIT_ON_CLOSE );
      caseStudy.pack();
      caseStudy.setVisible( true );
   }
}


 /**************************************************************************
 * (C) Copyright 1992-2003 by Deitel & Associates, Inc. and               *
 * Prentice Hall. All Rights Reserved.                                    *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
