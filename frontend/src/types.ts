export interface Usuario {
  id: string;
  nome: string;
  email: string;
  telefone: string;
  cpf: string;
  clubeAtivo: boolean;
}

export interface Produto {
  id: string;
  nome: string;
  descricao: string;
  imagemUrl: string;
  precoNormal: number;
  precoClube: number;
  estoque: number;
  ativo: boolean;
}