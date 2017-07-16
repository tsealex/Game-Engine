package graphics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class GFXCollector {

	private Map<Integer, Texture> idxTexMap;
	private Map<Integer, String> pathTexMap;
	private List<Texture> unsdTexList;

	private Map<Integer, VertexArray> idxVaoMap;
	private Map<Integer, String> clsVaoMap;
	private List<VertexArray> unsdVaoIdxList;

	protected GFXCollector() {
		idxTexMap = new HashMap<Integer, Texture>();
		pathTexMap = new HashMap<Integer, String>();
		unsdTexList = new ArrayList<Texture>();

		idxVaoMap = new HashMap<Integer, VertexArray>();
		clsVaoMap = new HashMap<Integer, String>();
		unsdVaoIdxList = new ArrayList<VertexArray>();
	}

	// //////////////////////////////////////
	// Textures //
	// //////////////////////////////////////

	protected Texture getTexture(int id) {
		return idxTexMap.get(id);
	}

	protected Texture getTexture(String path) {
		for (int id : pathTexMap.keySet()) {
			if (pathTexMap.get(id).equals(path))
				return idxTexMap.get(id);
		}
		return null;
	}

	protected int addTexture(Texture tex, String path) {
		idxTexMap.put(tex.ID(), tex);
		pathTexMap.put(tex.ID(), path);
		return tex.ID();
	}

	protected void updateTextureUsage(int id) {
		idxTexMap.get(id).decrUsage();
	}

	protected void detectUnusedTexture() {
		for (Texture tex : idxTexMap.values()) {
			if (tex.Usage() == 0 && !tex.MarkedDeletion()) {
				unsdTexList.add(tex);
				tex.mark();
			}
		}
	}

	protected void removeTexture() {
		List<Integer> temp = new ArrayList<Integer>();
		for (int i = 0; i < unsdTexList.size(); i++) {
			if (unsdTexList.get(i).Usage() == 0) {
				unsdTexList.get(i).delete();
				temp.add(unsdTexList.get(i).ID());
			} else {
				unsdTexList.get(i).unmark();
				unsdTexList.remove(i);
				i--;
			}
		}
		for (int t : temp) {
			idxTexMap.remove(t);
			pathTexMap.remove(t);
		}
		unsdTexList.clear();
	}

	private void clearTexture() {
		for (Texture tex : idxTexMap.values())
			tex.delete();
		unsdTexList.clear();
		idxTexMap.clear();
		pathTexMap.clear();
	}

	// //////////////////////////////////////
	// Vertex Array //
	// //////////////////////////////////////

	protected VertexArray getVAO(String className) {
		for (int id : idxVaoMap.keySet()) {
			if (clsVaoMap.get(id).equals(className))
				return idxVaoMap.get(id);
		}
		return null;
	}

	protected VertexArray getVAO(int id) {
		return idxVaoMap.get(id);
	}

	protected boolean containVAO(int id) {
		return idxVaoMap.containsKey(id);
	}

	protected int addVAO(VertexArray vao, String className) {
		idxVaoMap.put(vao.ID(), vao);
		clsVaoMap.put(vao.ID(), className);
		return vao.ID();
	}

	protected void updateVAOUsage(int id) {
		// When an object stops using the VAO, this function will be invoked
		// so that we can update the usage data of this VAO
		idxVaoMap.get(id).decrUsage();
	}

	protected void detectUnusedVAO() {
		for (VertexArray vao : idxVaoMap.values()) {
			if (vao.Usage() == 0 && !vao.MarkedDeletion()) {
				unsdVaoIdxList.add(vao);
				// If the VAO object is not used by anyone but is not marked for
				// deletion, mark it for deletion
				vao.mark();
			}
		}
	}

	protected void removeVAO() {
		// Remove all objects marked for deletion
		List<Integer> temp = new ArrayList<Integer>();
		for (int i = 0; i < unsdVaoIdxList.size(); i++) {
			if (unsdVaoIdxList.get(i).Usage() == 0) {
				unsdVaoIdxList.get(i).delete();
				temp.add(unsdVaoIdxList.get(i).ID());
			} else {
				unsdVaoIdxList.get(i).unmark();
				unsdVaoIdxList.remove(i);
				i--;
			}
		}
		for (int t : temp) {
			idxVaoMap.remove(t);
			clsVaoMap.remove(t);
		}
		unsdVaoIdxList.clear();
	}

	private void clearVAO() {
		for (VertexArray vao : idxVaoMap.values())
			vao.delete();
		unsdVaoIdxList.clear();
		idxVaoMap.clear();
		clsVaoMap.clear();
	}

	protected int getVAONumber() {
		return idxVaoMap.size();
	}

	protected int getTextureNumber() {
		return idxTexMap.size();
	}

	protected void clear() {
		clearVAO();
		clearTexture();
	}
}
