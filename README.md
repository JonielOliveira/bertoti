# Chatbot de Notícias com Telegram

Este projeto implementa um chatbot para o Telegram que realiza as seguintes funções:

1. **Receber áudios dos usuários**: Transcreve os áudios enviados para texto.
2. **Identificar tópicos de interesse**: Utiliza um modelo de linguagem (LLM) para interpretar o texto transcrito e identificar o assunto principal.
3. **Consultar a API de notícias do IBGE**: Faz requisições à API do IBGE para buscar notícias relacionadas ao tópico identificado.
4. **Gerar e enviar áudios de resposta**: Converte os títulos e introduções das notícias em áudio e envia ao usuário, junto com o link da notícia.

## Estrutura do Projeto

### Funcionalidades Principais
- **Transcrição de Áudio**: O modelo [OpenAI Whisper](https://github.com/openai/whisper) é usado para transcrever os áudios em texto.
- **Análise de Texto**: Utiliza o modelo de linguagem `Phi-3-mini-4k-instruct` para identificar os tópicos de interesse a partir do texto transcrito.
- **Consulta à API do IBGE**: Busca notícias relacionadas a termos específicos por meio da [API de notícias do IBGE](https://servicodados.ibge.gov.br/api/docs/noticias?versao=3).
- **Conversão de Texto em Áudio**: A biblioteca [Coqui TTS](https://github.com/coqui-ai/TTS) é usada para gerar áudios das notícias.
- **Bot do Telegram**: Configurado com a biblioteca `python-telegram-bot` para interagir com os usuários.

### Bibliotecas Utilizadas
- **Transcrição de áudio**: `openai-whisper`
- **Modelos de linguagem**: `llama_cpp_python`
- **TTS (Texto para Fala)**: `TTS`
- **Manipulação de áudio**: `pydub`
- **Processamento de áudio**: `ffmpeg`
- **Integração com Telegram**: `python-telegram-bot`
