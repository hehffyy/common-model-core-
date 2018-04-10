package com.butone.codeinf.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

import com.butone.codeinf.model.node.ConstantNode;
import com.butone.codeinf.model.node.DateNode;
import com.butone.codeinf.model.node.Node;
import com.butone.codeinf.model.node.ParameterNode;
import com.butone.codeinf.model.node.SequenceNode;
import com.butone.codeinf.model.node.VariableNode;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CodeDef {
	@XmlAttribute
	private String guid;
	@XmlAttribute
	private String name;
	@XmlElements({
			@XmlElement(name = "constantNode", type = ConstantNode.class),
			@XmlElement(name = "dateNode", type = DateNode.class),
			@XmlElement(name = "parameterNode", type = ParameterNode.class),
			@XmlElement(name = "sequenceNode", type = SequenceNode.class),
			@XmlElement(name = "variableNode", type = VariableNode.class) })
	private List<Node> nodes;

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	public List<Node> getNodes() {
		if (nodes == null)
			nodes = new ArrayList<Node>();
		return nodes;
	}

	public SequenceNode getSequenceNode() {
		for (Node node : this.getNodes()) {
			if (node instanceof SequenceNode) {
				return (SequenceNode) node;
			}
		}
		return null;
	}
}
