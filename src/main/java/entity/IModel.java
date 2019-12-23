package entity;

public interface IModel {
	public String[] getTableHeaders();
	public Object[] getTableRowData();
	Long getId();
	void updateWith(Object mask);
}
