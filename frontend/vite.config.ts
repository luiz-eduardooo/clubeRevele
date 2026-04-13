import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import tailwindcss from '@tailwindcss/vite' // Importa o plugin

export default defineConfig({
  plugins: [
    react(),
    tailwindcss(), // Ativa o plugin aqui
  ],
})