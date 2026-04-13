import { useState, type FormEvent } from "react";
import { type Usuario } from "../types";

interface LoginProps {
  onLogin: (usuario: Usuario) => void;
  onNavigateToCadastro: () => void; // <--- Adicionamos isso
}

export default function Login({ onLogin, onNavigateToCadastro }: LoginProps) {
  const [email, setEmail] = useState("");
  const [senha, setSenha] = useState("");
  const [erro, setErro] = useState("");

  const handleSubmit = async (e: FormEvent) => {
    e.preventDefault();
    setErro("");

    try {
      const response = await fetch("http://localhost:8080/api/usuarios/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, senha }),
      });

      if (response.ok) {
        const usuario: Usuario = await response.json();
        onLogin(usuario);
      } else {
        setErro("E-mail ou senha inválidos!");
      }
    } catch (error) {
      setErro("Erro ao conectar com o servidor. O backend está rodando?");
    }
  };

  return (
    <div className="flex min-h-screen items-center justify-center bg-zinc-100 px-4">
      <div className="w-full max-w-md rounded-2xl bg-white p-8 shadow-xl">
        <div className="mb-8 text-center">
          <h1 className="text-3xl font-bold text-revele-900">Revele Clube</h1>
          <p className="mt-2 text-zinc-500">Faça login para ver as ofertas exclusivas</p>
        </div>

        {erro && <div className="mb-4 rounded-lg bg-red-100 p-3 text-sm text-red-700">{erro}</div>}

        <form onSubmit={handleSubmit} className="space-y-4">
          <div>
            <label className="mb-1 block text-sm font-medium text-zinc-700">E-mail</label>
            <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} className="w-full rounded-lg border border-zinc-300 p-3 focus:border-revele-500 focus:outline-none focus:ring-1 focus:ring-revele-500" required />
          </div>
          <div>
            <label className="mb-1 block text-sm font-medium text-zinc-700">Senha</label>
            <input type="password" value={senha} onChange={(e) => setSenha(e.target.value)} className="w-full rounded-lg border border-zinc-300 p-3 focus:border-revele-500 focus:outline-none focus:ring-1 focus:ring-revele-500" required />
          </div>
          <button type="submit" className="w-full rounded-lg bg-revele-900 py-3 font-semibold text-revele-gold transition-colors hover:bg-revele-600 cursor-pointer">
            Entrar no Clube
          </button>
        </form>

        {/* --- Adicionamos o link pro Cadastro aqui --- */}
        <p className="mt-6 text-center text-sm text-zinc-600">
          Ainda não tem conta?{" "}
          <button onClick={onNavigateToCadastro} className="font-semibold text-revele-600 cursor-pointer hover:text-revele-900">
            Cadastre-se
          </button>
        </p>

      </div>
    </div>
  );
}