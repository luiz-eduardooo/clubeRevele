import { type Usuario } from "../types";

interface NavbarProps {
  usuario: Usuario;
  onLogout: () => void;
}

export default function Navbar({ usuario, onLogout }: NavbarProps) {
  return (
    <nav className="bg-white shadow-sm border-b border-revele-100">
      <div className="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8">
        <div className="flex h-16 justify-between items-center">
          <div className="flex items-center">
            <span className="text-2xl font-bold text-revele-900">Revele Clube</span>
          </div>
          <div className="flex items-center space-x-4">
            <span className="text-sm font-medium text-zinc-700">
              Olá, {usuario.nome}!
            </span>
            {usuario.clubeAtivo && (
              <span className="rounded-full bg-revele-900 px-3 py-1 text-xs font-bold text-revele-gold">
                MEMBRO VIP
              </span>
            )}
            <button
              onClick={onLogout}
              className="rounded-lg border border-zinc-200 px-4 py-2 text-sm font-medium text-zinc-600 transition hover:bg-zinc-50"
            >
              Sair
            </button>
          </div>
        </div>
      </div>
    </nav>
  );
}