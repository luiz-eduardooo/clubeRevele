import { type Produto } from "../types";

interface ProductCardProps {
  produto: Produto;
  clubeAtivo: boolean;
}

export default function ProductCard({ produto, clubeAtivo }: ProductCardProps) {
  const imagem = produto.imagemUrl || "https://placehold.co/400x300/e2e8f0/475569?text=Sem+Imagem";

  return (
    <div className="flex flex-col overflow-hidden rounded-2xl bg-white shadow-md transition-shadow hover:shadow-xl border border-zinc-100">
      <img src={imagem} alt={produto.nome} className="h-48 w-full object-cover" />
      
      <div className="flex flex-1 flex-col p-5">
        <h3 className="text-lg font-bold text-revele-900">{produto.nome}</h3>
        <p className="mt-1 flex-1 text-sm text-zinc-500 line-clamp-2">{produto.descricao}</p>
        
        <div className="mt-4 space-y-2">
          <div className={`flex items-center justify-between ${clubeAtivo ? 'text-zinc-400 line-through' : 'text-zinc-900'}`}>
            <span className="text-sm">Preço Normal:</span>
            <span className="font-semibold">R$ {produto.precoNormal.toFixed(2)}</span>
          </div>

          <div className={`flex items-center justify-between rounded-lg p-2 ${clubeAtivo ? 'bg-revele-100 text-revele-900' : 'bg-zinc-50 text-zinc-500'}`}>
            <span className="text-sm font-bold">Preço Clube:</span>
            <span className="text-xl font-black">R$ {produto.precoClube.toFixed(2)}</span>
          </div>
        </div>

        <button className="mt-6 w-full rounded-lg bg-revele-900 py-2.5 text-sm font-semibold text-revele-gold transition hover:bg-revele-600">
          Adicionar ao Carrinho
        </button>
      </div>
    </div>
  );
}