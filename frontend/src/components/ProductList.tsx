import { useState, useEffect } from "react";
import ProductCard from "./ProductCard";
import { type Produto, type Usuario } from "../types";

interface ProductListProps {
  usuario: Usuario;
}

export default function ProductList({ usuario }: ProductListProps) {
  const [produtos, setProdutos] = useState<Produto[]>([]);
  const [carregando, setCarregando] = useState(true);

  useEffect(() => {
    fetch("http://localhost:8080/api/produtos")
      .then((res) => res.json())
      .then((data: Produto[]) => {
        setProdutos(data);
        setCarregando(false);
      })
      .catch((err) => console.error("Erro ao buscar produtos:", err));
  }, []);

  if (carregando) {
    return <div className="mt-20 text-center text-revele-600 font-medium">Carregando vitrine...</div>;
  }

  return (
    <div className="mx-auto max-w-7xl px-4 py-8 sm:px-6 lg:px-8">
      <h2 className="mb-8 text-2xl font-bold text-revele-900">Nossos Cosméticos</h2>
      
      {produtos.length === 0 ? (
        <p className="text-zinc-500">Nenhum produto cadastrado no banco de dados ainda.</p>
      ) : (
        <div className="grid grid-cols-1 gap-6 sm:grid-cols-2 lg:grid-cols-4">
          {produtos.map((produto) => (
            <ProductCard 
              key={produto.id} 
              produto={produto} 
              clubeAtivo={usuario.clubeAtivo} 
            />
          ))}
        </div>
      )}
    </div>
  );
}