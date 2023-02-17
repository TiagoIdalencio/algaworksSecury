package com.algafood.repository.consultasMain;

public class ConsultaRestauranteMain {
//
//	public static void main(String[] args) {
//		ApplicationContext applicationContext = new SpringApplicationBuilder(CozinhaApplication.class)
//				.web(WebApplicationType.NONE).run(args);

//		RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);
//		CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);
//
//		Cozinha cozinha1 = new Cozinha();
//		cozinha1.setId(1L);
//		cozinha1.setNome("AFRO");
//		cozinhaRepository.salvar(cozinha1);
//
//		Cozinha cozinha2 = new Cozinha();
//		cozinha2 = cozinhaRepository.buscar(2L);
//
//		Cozinha cozinha3 = new Cozinha();
//		cozinha3.setNome("CozinhaBRASIL");
//		cozinha3 = cozinhaRepository.salvar(cozinha3);
//
//		Cozinha cozinha4 = new Cozinha();
//		cozinha4.setNome("CozinhaDELETADA");
//		cozinha4 = cozinhaRepository.salvar(cozinha4);
//
//		// System.out.println(cozinha2.getId() + " - " + cozinha2.getNome());
//
//		Restaurante restaurante3 = new Restaurante();
//		restaurante3.setCozinha(cozinha2);
//		restaurante3.setNome("Restaurante Tiagueras");
//		BigDecimal bigd = new BigDecimal(2.2);
//		restaurante3.setTaxaFrete(bigd);
//
//		restauranteRepository.salvar(restaurante3);
//
//		List<Restaurante> restaurantes = restauranteRepository.listar();
//
//		for (int i = 0; restaurantes.size() > i; i++) {
//			Restaurante restaurante = restaurantes.get(i);
//
//			if (restaurante.getId() == 2) {
//				restaurante.setNome("AGORA_SIM");
//				restauranteRepository.salvar(restaurante);
//			}
//
//			System.out.println(restaurante.getId() + " - " + restaurante.getNome() + " - T: "
//					+ restaurante.getTaxaFrete() + " OBJ: " + restaurante.getCozinha().getNome());
//		}
//
//		List<Cozinha> cozinhas = cozinhaRepository.listar();
//
//		for (int i = 0; cozinhas.size() > i; i++) {
//			Cozinha cozinha = cozinhas.get(i);
//
//			System.out.println(cozinha.getId() + " - " + cozinha.getNome());
//		}
//
//
//		//cozinhaRepository = applicationContext.getBean(CozinhaRepositoryImpl.class);
//		cozinhaRepository.remover(cozinha4.getId());
//
//		cozinhas = cozinhaRepository.listar();
//
//		for (int i = 0; cozinhas.size() > i; i++) {
//			Cozinha cozinha = cozinhas.get(i);
//
//			System.out.println(cozinha.getId() + " - " + cozinha.getNome());
//		}
//
//	}

}
