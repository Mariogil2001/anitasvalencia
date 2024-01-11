package p2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class AccesoBDTest {

	@Test
	void testObtenerPedido() {
		AccesoBD bd = AccesoBD.getInstance();
		ArrayList<PedidoBD> pedidos = bd.obtenerPedidos(1);
		assertTrue(pedidos!=null && pedidos.size()>0);
	}

}
