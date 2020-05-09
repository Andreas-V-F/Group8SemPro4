package dk.sdu.mmmi.cbse.osgiplayer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import sdu.mmmi.softwareengineering.osgicommon.services.IEntityProcessingService;
import sdu.mmmi.softwareengineering.osgicommon.services.IGamePluginService;

public class Activator implements BundleActivator {

    public void start(BundleContext context) throws Exception {
        context.registerService(IGamePluginService.class, new PlayerPlugin(), null);
        context.registerService(IEntityProcessingService.class, new PlayerProcessor(), null);
    }

    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }

}


//
//# Get the current node
//        current_node = open_list[0]
//        current_index = 0
//        for index, item in enumerate(open_list):
//            if item.f < current_node.f:
//                current_node = item
//                current_index = index
//
//        # Pop current off open list, add to closed list
//        open_list.pop(current_index)
//        closed_list.append(current_node)
//
//        # Found the goal
//        if current_node.STATE[0] == GOAL_STATE_01[0] or current_node.STATE[0] == GOAL_STATE_02[0]:
//            return current_node.path()
//
//        # Generate children
//        children = []
//        for child in EXPAND(current_node):
//            children.append(child)
//
//        # Loop through children
//        for child in children:
//
//            # Child is on the closed list
//            for closed_child in closed_list:
//                if child == closed_child:
//                    continue
//
//            # Create the f, g, and h values
//            child.g = child.STATE[1] + child.PARENT_NODE.g
//            child.h = HEURISTICS_SPACE[child.STATE[0]]
//            child.f = child.g + (1 * child.h) # weigth = 1, , should output K as the goal node
//            # child.f = child.g + (weight * child.h) # weight = 2.5, should output L as the goal node
//
//            # Child is already in the open list
//            for open_node in open_list:
//                if child == open_node and child.g > open_node.g:
//                    continue
//
//            # Add the child to the open list
//            open_list.append(child)



// # Generate children
//        children = []
//        for new_position in [(0, -1), (0, 1), (-1, 0), (1, 0), (-1, -1), (-1, 1), (1, -1), (1, 1)]: # Adjacent squares
//
//            # Get node position
//            node_position = (current_node.position[0] + new_position[0], current_node.position[1] + new_position[1])
//
//            # Make sure within range
//            if node_position[0] > (len(maze) - 1) or node_position[0] < 0 or node_position[1] > (len(maze[len(maze)-1]) -1) or node_position[1] < 0:
//                continue
//
//            # Make sure walkable terrain
//            if maze[node_position[0]][node_position[1]] != 0:
//                continue
//
//            # Create new node
//            new_node = Node(current_node, node_position)
//
//            # Append
//            children.append(new_node)
//
//        # Loop through children
//        for child in children:
//
//            # Child is on the closed list
//            for closed_child in closed_list:
//                if child == closed_child:
//                    continue
//
//            # Create the f, g, and h values
//            child.g = current_node.g + 1
//            child.h = ((child.position[0] - end_node.position[0]) ** 2) + ((child.position[1] - end_node.position[1]) ** 2)
//            child.f = child.g + child.h
//
//            # Child is already in the open list
//            for open_node in open_list:
//                if child == open_node and child.g > open_node.g:
//                    continue
//
//            # Add the child to the open list
//            open_list.append(child)