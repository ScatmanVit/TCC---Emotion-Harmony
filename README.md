# Emotion Harmony - Sistema de Gerenciamento Emocional, bem-estar e autocuidado

![Licença](https://img.shields.io/badge/Licen%C3%A7a-MIT-green)

Este projeto é o Trabalho de Conclusão de Curso (TCC) desenvolvido para o curso Técnico em Desenvolvimento de Sistemas na Etec Lauro Gomes.  
O Emotion Harmony é um sistema completo que integra aplicativo mobile, interface web e backend para ajudar usuários a gerenciar seu bem-estar emocional.

---

## ✨ Visão Geral

O Emotion Harmony oferece:

- Diário emocional para registro diário de sentimentos  
- Acompanhamento de estados emocionais ao longo do tempo  
- Recursos educacionais sobre saúde mental  
- Sistema integrado entre mobile, web e servidor  

---

## 🧩 Componentes do Sistema

| Componente         | Tecnologias Principais        | Status                             |
|--------------------|------------------------------|----------------------------------|
| Aplicativo Android | Java, Android SDK            | Versão APK disponível (emotion.apk) |
| Interface Web      | JavaScript, HTML, CSS        | Pré-finalizada                   |
| Backend Server     | JavaScript (Node.js)         | Backup disponível                |

---

## 📊 Estrutura do Projeto

```plaintext
emotion-harmony/
├── android/            # Código-fonte do aplicativo Android
├── server/             # Backend e lógica de servidor
├── telas/              # Design de interface e telas (.rar disponível)
├── web/user/           # Interface web do usuário
│
├── modelo fisico.png   # Diagrama físico do sistema
├── schema database.png # Esquema do banco de dados
├── emotion.apk         # Versão instalável para Android
│
├── .gitignore          # Arquivos ignorados pelo Git
├── LICENSE             # Licença MIT
├── package.json        # Dependências do projeto
└── README.md           # Documentação do projeto
```
---

## 🛠️ Tecnologias Utilizadas
Frontend Mobile: Java (57.8%)

- Backend & Web: JavaScript (37.7%), CSS (4.4%), HTML (0.1%)
- Banco de Dados: MySql
- Ferramentas: Yarn e Npm (gerenciamento de pacotes)

## 📱 Funcionalidades Principais
- Aplicativo Android
- Registro diário de emoções
- Acompanhamento do histórico emocional
- Visualização de gráficos e insights
  
### Painel Web
- Dashboard de acompanhamento
- Gerenciamento de usuários (rota DELETE implementada)
Recuperação de senha
- Análise de dados agregados

### Backend
- API RESTful em node.js para integração mobile/web
- Autenticação de usuários
- Armazenamento seguro de dados emocionais
- Geração de relatórios e estatísticas


👥 Autores

Equipe Emotion Harmony

Sergio Augusto Moreira Bastos - Desenvolvedor principal Back e Front do Site e App
Victor Ribeiro Baradel - Desenvolvedor Front e Back do Site, e documentador.
Yasmim Raynara da Silveira Silva - Modelagem de dados e criação do banco de dados
Luiz Alberto de Carvalho Holanda Junior - Desenvolvedor Back e Front do App
Gabriel de Oliveira Dozzi Tezza - Desenvolvedor Front e Back do Site, e documentador.



📄 Licença
Este projeto está licenciado sob a Licença MIT - veja o arquivo LICENSE para detalhes.

Desenvolvido como trabalho de conclusão de curso na Etec Lauro Gomes
2025 - Técnico em Desenvolvimento de Sistemas
