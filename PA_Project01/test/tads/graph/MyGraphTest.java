/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tads.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tiago
 */
public class MyGraphTest {

    private static MyGraph<Integer, String> graph;

    public MyGraphTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        graph = new MyGraph();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void insertVertex_1_InsertingVertex() {
        graph.insertVertex(1);

        for (Vertex<Integer> v : graph.vertices()) {
            assertEquals("O valor retornado é diferente do inserido", (long) 1, (long) v.element());
            break;
        }

    }

    @Test
    public void insertEdge_Aresta1_InsertingEdge() {
        graph.insertVertex(1);
        graph.insertVertex(2);
        graph.insertEdge(1, 2, "Aresta 1");

        for (Edge<String, Integer> e : graph.edges()) {
            assertEquals("O valor retornado é diferente do inserido", "Aresta 1", e.element());
            break;
        }
    }

    @Test
    public void numVertices_0_NoVertices() {
        assertEquals("O grafo não tem vertices", 0, graph.numVertices());
    }

    @Test
    public void numVertices_2_WithVertices() {
        graph.insertVertex(1);
        graph.insertVertex(2);
        assertEquals("O grafo tem vertices", 2, graph.numVertices());
    }

    @Test
    public void numEdges_0_NoEdges() {
        assertEquals("O grafo não tem arestas", 0, graph.numEdges());
    }

    @Test
    public void numEdges_2_WithEdges() {
//        graph.insertVertex(1);
//        graph.insertVertex(2);
//        graph.insertVertex(3);
//        graph.insertVertex(4);
//        graph.insertEdge(1, 2, "Aresta 1");
//        graph.insertEdge(3, 4, "Aresta 2");

        for (int i = 1; i <= 4; i++) {
            graph.insertVertex(i);
            if (i % 2 == 0) {
                graph.insertEdge(i - 1, i, "Aresta " + i / 2);
            }
        }

        assertEquals("O grafo tem arestas", 2, graph.numEdges());
    }

    @Test
    public void vertices_False_NoVertices() {
        Iterator it = graph.vertices().iterator();
        assertEquals("A coleçao deve estar vazia", false, it.hasNext());
    }

    @Test
    public void vertices_True_WithVertices() {
        graph.insertVertex(1);
        
        HashMap<Integer, Vertex<Integer>> hashMapAux = new HashMap<>();
        int i = 0;
        for(Vertex<Integer> v : graph.vertices()){
            hashMapAux.put(i, v);
            i++;
        }
//        for(Vertex<Integer> v : graph.vertices()){
//            assertEquals("Os elementos devem ser iguais", arrayAux.get(0), v);
//        }

        assertEquals("As coleções devem ser iguais", 
                     hashMapAux.values(), graph.vertices());
    }

    @Test
    public void edges_True_NoEdges() {
        Iterator it = graph.edges().iterator();
        assertEquals("A coleçao deve estar vazia", false, it.hasNext());
    }

    @Test
    public void edges_True_WithEdges() {
        graph.insertVertex(1);
        graph.insertVertex(2);
        graph.insertEdge(1, 2, "Aresta 1");
        
        HashSet<Edge<String, Integer>> hashAux = new HashSet<>();
        
        for(Edge<String, Integer> e : graph.edges()){
            hashAux.add(e);
        }
        

        assertEquals("Os elementos devem ser iguais", hashAux, graph.edges());
    }

    @Test
    public void replace_2_ReplacingVertexElement() {
        graph.insertVertex(1);
        Vertex<Integer> vertex = null;
        for (Vertex<Integer> v : graph.vertices()) {
            vertex = v;
            break;
        }
        graph.replace(vertex, 2);

        int i = 0;
        for (Vertex<Integer> ve : graph.vertices()) {
            i = ve.element();
            break;
        }
        assertEquals("O vertice tem o valor '2'", 2, i);
    }

    @Test
    public void replace_2_ReplacingEdgeElement() {
        graph.insertVertex(1);
        graph.insertVertex(2);
        graph.insertEdge(1, 2, "Aresta 1");

        Edge<String, Integer> edge = null;
        for (Edge<String, Integer> e : graph.edges()) {
            edge = e;
            break;
        }

        graph.replace(edge, "Aresta 2");

        String s = "";
        for (Edge<String, Integer> ed : graph.edges()) {
            s = ed.element();
            break;
        }

        assertEquals("A aresta tem o valor 'Aresta 2'", "Aresta 2", s);

    }

    @Test
    public void incidentEdges_True_WithoutEdges() {
        graph.insertVertex(1);
        graph.insertVertex(2);
        graph.insertVertex(3);
        graph.insertEdge(2, 3, "Aresta 1");

        for (Vertex<Integer> v : graph.vertices()) {
            if (v.element() == 1) {
                assertEquals("Coleçao deve estar vazia",
                        true, ((ArrayList) graph.incidentEdges(v)).isEmpty());
                break;
            }
        }
    }

    @Test
    public void incidentEdges_True_WithEdges() {
        graph.insertVertex(1);
        graph.insertVertex(2);
        graph.insertEdge(1, 2, "Aresta 1");

        for (Vertex<Integer> v : graph.vertices()) {
            if (v.element() == 1) {
                for (Edge<String, Integer> e : graph.incidentEdges(v)) {
                    assertEquals("A aresta deve ter o valor \"Aresta 1\"",
                            "Aresta 1", e.element());
                }
            }
        }
    }

    @Test
    public void opposite_2_OppositeVertex() {
        graph.insertVertex(1);
        graph.insertVertex(2);
        graph.insertEdge(1, 2, "Aresta 1");

        for (Vertex<Integer> v : graph.vertices()) {
            if (v.element() == 1) {
                for (Edge<String, Integer> e : graph.incidentEdges(v)) {
                    assertEquals("O vertice deve ter o valor \"2 \"",
                            (long) 2, (long) graph.opposite(v, e).element());
                }
            }
        }
    }

    @Test
    public void areAdjacent_True_WithAdjacent() {
        graph.insertVertex(1);
        graph.insertVertex(2);
        graph.insertEdge(1, 2, "Aresta 1");

        Vertex<Integer> aux = null;

        for (Vertex<Integer> v : graph.vertices()) {
            if (aux == null) {
                aux = v;
                continue;
            }
            assertEquals("Os vertices são adjacentes", true, graph.areAdjacent(aux, v));
        }
    }

    @Test
    public void areAdjacent_False_WithoutAdjacent() {
        graph.insertVertex(1);
        graph.insertVertex(2);

        Vertex<Integer> aux = null;

        for (Vertex<Integer> v : graph.vertices()) {
            if (aux == null) {
                aux = v;
                continue;
            }
            assertEquals("Os vertices não são adjacentes", false, graph.areAdjacent(aux, v));
        }
    }

    @Test
    public void removeVertex_RightVertex_RemovingVertex() {
        graph.insertVertex(1);
        for (Vertex<Integer> v : graph.vertices()) {
            assertEquals("O valor removido deve ser \"1 \"",
                    (long) 1, (long) graph.removeVertex(v));
            break;
        }
    }

    @Test
    public void removeEdge_RightEdge_RemovingEdge() {
        graph.insertVertex(1);
        graph.insertVertex(2);
        graph.insertEdge(1, 2, "Aresta 1");
        for (Edge<String, Integer> e : graph.edges()) {
            assertEquals("O valor removido deve ser \"Aresta 1\"",
                     "Aresta 1", graph.removeEdge(e));
            break;
        }
    }

    
    @Test
    public void removeVertex_0_RemovingVertex(){
        graph.insertVertex(1);
        for(Vertex<Integer> v : graph.vertices()){
            graph.removeVertex(v);
        }
        
        assertEquals("O grafo não deve ter elementos", 0, graph.numVertices());
    }
    
    @Test
    public void removeEdge_0_RemovingEdge(){
        graph.insertVertex(1);
        graph.insertVertex(2);
        graph.insertEdge(1, 2, "Aresta 1");
        for(Edge<String, Integer> e : graph.edges()){
            graph.removeEdge(e);
            break;
        }
        
        assertEquals("O grafo não deve ter elementos", 0, graph.numEdges());
    }
}
