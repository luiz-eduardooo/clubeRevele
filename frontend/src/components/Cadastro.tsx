import { useState,type FormEvent } from "react";

interface CadastroProps {
  onNavigateToLogin: () => void;
}

export default function Cadastro({ onNavigateToLogin }: CadastroProps) {
  const [formData, setFormData] = useState({
    nome: "",
    email: "",
    senha: "",
    cpf: "",
    telefone: "",
  });
  const [erro, setErro] = useState("");
  const [sucesso, setSucesso] = useState(false);

  const handleSubmit = async (e: FormEvent) => {
    e.preventDefault();
    setErro("");

    try {
      const response = await fetch("http://localhost:8080/api/usuarios", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        // Enviamos clubeAtivo como false por padrão (ele tem que assinar depois)
        body: JSON.stringify({ ...formData, clubeAtivo: false }),
      });

      if (response.ok) {
        setSucesso(true);
        // Espera 2 segundinhos e manda o cara pro Login
        setTimeout(() => {
          onNavigateToLogin();
        }, 2000);
      } else {
        setErro("Erro ao cadastrar. Verifique os dados e tente novamente.");
      }
    } catch (error) {
      setErro("Erro ao conectar com o servidor.");
    }
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  return (
    <div className="flex min-h-screen items-center justify-center bg-zinc-100 px-4 py-8">
      <div className="w-full max-w-md rounded-2xl bg-white p-8 shadow-xl">
        <div className="mb-8 text-center">
          <h1 className="text-3xl font-bold text-revele-900">Junte-se ao Clube</h1>
          <p className="mt-2 text-zinc-500">Crie sua conta e aproveite os benefícios</p>
        </div>

        {erro && <div className="mb-4 rounded-lg bg-red-100 p-3 text-sm text-red-700">{erro}</div>}
        {sucesso && <div className="mb-4 rounded-lg bg-green-100 p-3 text-sm text-green-700">Cadastro realizado! Redirecionando...</div>}

        <form onSubmit={handleSubmit} className="space-y-4">
          <div>
            <label className="mb-1 block text-sm font-medium text-zinc-700">Nome Completo</label>
            <input name="nome" type="text" required value={formData.nome} onChange={handleChange} className="w-full rounded-lg border border-zinc-300 p-3 focus:border-revele-500 focus:outline-none focus:ring-1 focus:ring-revele-500" placeholder="Maria da Silva" />
          </div>
          
          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="mb-1 block text-sm font-medium text-zinc-700">CPF</label>
              <input name="cpf" type="text" required value={formData.cpf} onChange={handleChange} className="w-full rounded-lg border border-zinc-300 p-3 focus:border-revele-500 focus:outline-none focus:ring-1 focus:ring-revele-500" placeholder="000.000.000-00" />
            </div>
            <div>
              <label className="mb-1 block text-sm font-medium text-zinc-700">Telefone</label>
              <input name="telefone" type="text" required value={formData.telefone} onChange={handleChange} className="w-full rounded-lg border border-zinc-300 p-3 focus:border-revele-500 focus:outline-none focus:ring-1 focus:ring-revele-500" placeholder="(11) 99999-9999" />
            </div>
          </div>

          <div>
            <label className="mb-1 block text-sm font-medium text-zinc-700">E-mail</label>
            <input name="email" type="email" required value={formData.email} onChange={handleChange} className="w-full rounded-lg border border-zinc-300 p-3 focus:border-revele-500 focus:outline-none focus:ring-1 focus:ring-revele-500" placeholder="seu@email.com" />
          </div>

          <div>
            <label className="mb-1 block text-sm font-medium text-zinc-700">Senha</label>
            <input name="senha" type="password" required value={formData.senha} onChange={handleChange} className="w-full rounded-lg border border-zinc-300 p-3 focus:border-revele-500 focus:outline-none focus:ring-1 focus:ring-revele-500" placeholder="••••••••" />
          </div>

          <button type="submit" className="w-full rounded-lg bg-revele-900 py-3 font-semibold text-revele-gold transition-colors hover:bg-revele-600 cursor-pointer">
            Criar Conta
          </button>
        </form>

        <p className="mt-6 text-center text-sm text-zinc-600">
          Já tem uma conta?{" "}
          <button onClick={onNavigateToLogin} className="font-semibold cursor-pointer text-revele-600 hover:text-revele-900">
            Faça Login
          </button>
        </p>
      </div>
    </div>
  );
}