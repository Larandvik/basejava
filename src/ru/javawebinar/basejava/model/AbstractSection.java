package ru.javawebinar.basejava.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class AbstractSection implements Serializable {
}
