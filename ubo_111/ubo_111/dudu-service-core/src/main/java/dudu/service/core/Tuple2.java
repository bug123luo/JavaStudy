package dudu.service.core;

import java.io.Serializable;

public class Tuple2<K,V> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1727121420287530302L;
	public K _1;
	public V _2;
	
	public Tuple2(K _1, V _2) {
		this._1 = _1;
		this._2 = _2;
	}

	public K get_1() {
		return _1;
	}

	public void set_1(K _1) {
		this._1 = _1;
	}

	public V get_2() {
		return _2;
	}

	public void set_2(V _2) {
		this._2 = _2;
	}

	public K _1() {
		return this._1;
	}
	
	public V _2() {
		return this._2;
	}
}
