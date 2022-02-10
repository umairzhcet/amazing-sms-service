package com.twilio.interviews.sms;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CarrierNode {

	public String carrierName;

	/**
	 * Regular expression for numbers terminating in this carrier
	 */
	Optional<Pattern> terminationRegex;

	/**
	 * Map of carriers connected to this one. The key is a regular expression of the
	 * numbers allowed to flow through that carrier
	 */
	public Map<Pattern, CarrierNode> connectedCarriers;

	/**
	 * Given a phone number discover the connected CarrierNodes that are valid for
	 * that number
	 */
	public Set<CarrierNode> getConnectedCarrierNodes(String phoneNumber) {

		Set<CarrierNode> nodes = new HashSet<>();

		for (Entry<Pattern, CarrierNode> pattern : connectedCarriers.entrySet()) {

			Matcher m = pattern.getKey().matcher(phoneNumber);
			if (m.matches()) {
				nodes.add(pattern.getValue());
			}

		}

		return nodes;
	}

	// Constructors
	public CarrierNode(String carrierName, Optional<String> terminationRegex,
			Map<Pattern, CarrierNode> connectedCarriers) {
		this.carrierName = carrierName;
		this.terminationRegex = terminationRegex.map(regex -> Pattern.compile(regex));
		this.connectedCarriers = connectedCarriers;
	}

	public CarrierNode(String carrierName) {
		this(carrierName, null);
	}

	public CarrierNode(String carrierName, String terminationRegularExpression) {
		this(carrierName, Optional.ofNullable(terminationRegularExpression), new HashMap<>());
	}

	public CarrierNode addConnectedCarrier(String connectionRegex, CarrierNode carrierNode) {
		connectedCarriers.put(Pattern.compile(connectionRegex), carrierNode);
		return this;
	}

	public Collection<CarrierNode> getAllConnectedCarriers() {
		return connectedCarriers.values();
	}

	public String getConnectedCarrierRegex(CarrierNode otherNode) {
		return connectedCarriers.entrySet().stream().filter(e -> e.getValue().equals(otherNode)).map(Map.Entry::getKey)
				.map(Pattern::toString).findFirst().orElse(null);
	}

	/**
	 * Given a phone number, does it match the `terminationRegex` for this Node or
	 * not.
	 */
	public boolean numberTerminatesAtThisCarrier(String phoneNumber) {
		return terminationRegex.map(regex -> regex.matcher(phoneNumber).matches()).orElse(false);
	}

}
