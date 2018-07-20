package de.quandoo.recruitment.registry.model.cusine;

public class Cuisine {

	private String cuisineName;

	public Cuisine(String cuisineName) {
		this.cuisineName = cuisineName;
	}

	/**
	 * @return the cuisineName
	 */
	public String getCuisineName() {
		return cuisineName;
	}

	/**
	 * @param cuisineName the cuisineName to set
	 */
	public void setCuisineName(String cuisineName) {
		this.cuisineName = cuisineName;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cuisineName == null) ? 0 : cuisineName.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Cuisine)) {
			return false;
		}
		Cuisine other = (Cuisine) obj;
		if (cuisineName == null) {
			if (other.cuisineName != null) {
				return false;
			}
		} else if (!cuisineName.equals(other.cuisineName)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Cuisine [cuisineName=" + cuisineName + "]";
	}
}