import { useState, useEffect } from "react";
import Login from "./components/Login";
import Cadastro from "./components/Cadastro";
import Navbar from "./components/Navbar";
import ProductList from "./components/ProductList";
import { type Usuario } from "./types";

export default function App() {
  const [usuario, setUsuario] = useState<Usuario | null>(null);
  // Estado para controlar qual tela mostrar antes de logar
  const [telaAtual, setTelaAtual] = useState<'login' | 'cadastro'>('login');

  useEffect(() => {
    const usuarioSalvo = localStorage.getItem("clubeRevele_user");
    if (usuarioSalvo) {
      setUsuario(JSON.parse(usuarioSalvo) as Usuario);
    }
  }, []);

  const handleLogin = (dadosUsuario: Usuario) => {
    localStorage.setItem("clubeRevele_user", JSON.stringify(dadosUsuario));
    setUsuario(dadosUsuario);
  };

  const handleLogout = () => {
    localStorage.removeItem("clubeRevele_user");
    setUsuario(null);
    setTelaAtual('login'); // Volta pro login ao sair
  };

  // Se o usuário estiver logado, mostra o site!
  if (usuario) {
    return (
      <div className="min-h-screen bg-zinc-50 font-sans">
        <Navbar usuario={usuario} onLogout={handleLogout} />
        <ProductList usuario={usuario} />
      </div>
    );
  }

  // Se não estiver logado, decide qual tela mostrar
  return (
    <div className="min-h-screen bg-zinc-50 font-sans">
      {telaAtual === 'login' ? (
        <Login 
          onLogin={handleLogin} 
          onNavigateToCadastro={() => setTelaAtual('cadastro')} 
        />
      ) : (
        <Cadastro 
          onNavigateToLogin={() => setTelaAtual('login')} 
        />
      )}
    </div>
  );
}