package com.twilio.interviews.sms;

import java.util.*;
import java.util.regex.Pattern;

/**
 * Sample class that generated a DOT graph representation to visualize the carrier graph
 * Execute this class to output the graph definition.
 * An easy way to view the image is to use it in `https://dreampuf.github.io/GraphvizOnline/`
 */
public class GraphDOTBuilder {

    public static String buildCarrierGraphDOTRepresentation(CarrierNode gatewayNode) {

        Stack<CarrierNode> nodes = new Stack<>();
        nodes.push(gatewayNode);

        Set<CarrierNode> nodesLabels = new HashSet<>();
        Map<CarrierNode, Collection<CarrierNode>> nodesConnectedMap = new HashMap<>();

        // collect the nodes and build auxiliary data structures
        while (!nodes.isEmpty()) {
            CarrierNode node = nodes.pop();
            Collection<CarrierNode> carrierNodes = node.getAllConnectedCarriers();

            nodesConnectedMap.put(node, carrierNodes);
            nodesLabels.add(node);

            for (CarrierNode connectedCarrierNode : carrierNodes) {
                if (!nodesLabels.contains(connectedCarrierNode)) {
                    nodes.push(connectedCarrierNode);
                }
            }
        }

        // Build the directed graph definition
        StringBuffer edgesStr = new StringBuffer();
        StringBuffer nodesStr = new StringBuffer();

        for (CarrierNode node : nodesLabels) {

            String nodeID = getNodeID(node);
            String carrierTerminationRegex = node.terminationRegex.map(Pattern::toString).orElse("<none>");
            String attributes = String.format("label=\"%s - %s \"", node.carrierName, carrierTerminationRegex);

            if (gatewayNode == node) {
                attributes += ", color=\"red\", shape=\"invhouse\"";
            } else {
                attributes += ", shape=\"box\"";
            }

            if (node.getAllConnectedCarriers().isEmpty()) {
                attributes += ", color=\"blue\", shape=\"house\"";
            }
            nodesStr.append(String.format("\t%s [%s];\n", nodeID, attributes));

            for (CarrierNode connectedNode : nodesConnectedMap.get(node)) {
                String connectedNodeID = getNodeID(connectedNode);
                edgesStr.append(String.format("\t%s -> %s [label=\"%s\"];\n", getNodeID(node), connectedNodeID, node.getConnectedCarrierRegex(connectedNode)));
            }
        }
        return String.format("digraph graphname {\n%s\n%s\n}", nodesStr, edgesStr);
    }

    private static String getNodeID(CarrierNode node) {
        return node.carrierName.trim().replaceAll("\\s", "_");
    }

    public static final void main(String... args) {
        CarrierNode gatewayNode = CarrierService.getCarrierNetworkGateway();
        System.out.println(buildCarrierGraphDOTRepresentation(gatewayNode));
    }

}
