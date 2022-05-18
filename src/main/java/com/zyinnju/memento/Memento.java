package com.zyinnju.memento;

import com.zyinnju.draw.AbstractContent;
import lombok.Data;
import lombok.Getter;

/**
 * @author Zyi
 */
class Memento {

	private AbstractContent content;

	Memento(Originator originator) {
		content = originator.getContent();
	}

	AbstractContent getContent() {
		return content;
	}

	void setContent(AbstractContent content) {
		this.content = content;
	}
}
