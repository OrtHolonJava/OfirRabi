

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Map 
{
	private int _size;
	private int _counter = 0;
	private int[][] _map;
	private int _rows;
	private int _columns;
	
	/**
	 * creates new map object
	 * @param size
	 * @param sizeW
	 * @param fileName
	 */
	public Map(int size, int sizeW, String fileName)
	{
		_map = new int[size][sizeW];
		_size=sizeW;
		_rows=size;
		_columns=sizeW;
		try 
		{
			File file = new File(fileName);

			DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

			Document doc = docBuilder.parse(file);

			if (doc.hasChildNodes()) 
			{
				readNode(doc.getChildNodes());
			}

		} 
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
	}
	public int getRows()
	{
		return _rows;
	}
	public int getColumns()
	{
		return _columns;
	}
	/**
	 * return map matrix of int
	 * @return
	 */
	public int[][] get_map() 
	{
		return _map;
	}
	/**
	 * read all nodes of xml file and creates int matrix of the map
	 * @param nodeList
	 */
	private void readNode(NodeList nodeList) 
	{
		for (int count = 0; count < nodeList.getLength(); count++) 
		{
			Node tempNode = nodeList.item(count);

			if (tempNode.getNodeType() == Node.ELEMENT_NODE) 
			{
				if (tempNode.hasAttributes()) 
				{
					NamedNodeMap nodeMap = tempNode.getAttributes();

					for (int i = 0; i < nodeMap.getLength(); i++) 
					{
						Node node = nodeMap.item(i);
						_map[_counter/_size][_counter%_size] = Integer.parseInt(node.getNodeValue()); 
						_counter++;
					}
				}

				if (tempNode.hasChildNodes()) 
				{
					readNode(tempNode.getChildNodes());
				}
			}
		}
	}
	
	public static int getElementCountByName(String fileName,String name)
	{
		try{
	
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(fileName);
		NodeList list = doc.getElementsByTagName(name);
		return  list.getLength();
		}
		catch(Exception e)
		{
			System.out.println("exception: "+e.getMessage());
			return -1;
		}	
	}
}
