-- Dados de exemplo (tutores + pets)
INSERT INTO tutores (id, nome, email, telefone, endereco, senha, observacoes)
VALUES
  (1, 'Arthur', 'arthur@example.com', '11999990001', 'Rua das Flores, 123', null, 'Tutor do Bisteca'),
  (2, 'Brenda', 'brenda@example.com', '11999990002', 'Av. Central, 456', null, 'Tutor da Kiwi'),
  (3, 'Thiago', 'thiago@example.com', '11999990003', 'Travessa Azul, 789', null, 'Tutor da Crystal');

INSERT INTO pets (id, nome, especie, raca, idade, cor, observacoes, tutor_id, bairro, latitude, longitude, foto_url)
VALUES
  (1, 'Bisteca', 'Cachorro', 'Vira-lata', 4, 'Caramelo', 'Brincalhão e dócil', 1, 'Centro', null, null, 'https://placehold.co/400x300?text=Bisteca'),
  (2, 'Kiwi', 'Cachorro', 'Shih Tzu', 2, 'Cinza', 'Gosta de ficar na janela', 2, 'Centro', null, null, 'https://placehold.co/400x300?text=Kiwi'),
  (3, 'Crystal', 'Cachorro', 'Poodle', 6, 'Branco', 'Muito calma', 3, 'Centro', null, null, 'https://placehold.co/400x300?text=Crystal');

-- Localizador: Leandra procurando cachorra idosa, pequeno porte, vira-lata, cor preto e branco
INSERT INTO localizadores (email, endereco, bairro, nome, observacoes, senha, telefone, foto_url, latitude, longitude, encontrado, id)
VALUES ('leandra@example.com', 'Rua das Acácias, 222', 'Jd Holanda', 'Leandra',
        'Procurando cachorra Baby: idosa, pequeno porte, vira-lata, cor preto e branco',
        null, '11988887777', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTgawMDRyD8_BleXXypVieZDNfMviSqAQxobw&s', null, null, false, default);

-- Mais animais perdidos (exemplo)
INSERT INTO localizadores (email, endereco, bairro, nome, observacoes, senha, telefone, foto_url, latitude, longitude, encontrado, id)
VALUES 
('carlos@example.com', 'Rua Verde, 55', 'Jardim Paulista', 'Carlos',
 'Procurando cão Caramelo: porte médio, vira-lata, cor marrom com manchas brancas',
 null, '11977776666', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSJSf-qnjm36BaR8jTPPMcI_shFr6l81nJL4A&s', null, null, false, default),
('marina@example.com', 'Av. Oceano, 999', 'Centro', 'Marina',
 'Procurando gata Catarina: siamesa, pequena, olhos azuis, pelo claro',
 null, '11966665555', 'https://st3.depositphotos.com/5574548/13341/i/450/depositphotos_133411086-stock-photo-siamese-cat-blue-eyes-looking.jpg', null, null, false, default);

