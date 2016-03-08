# Transaction Simulator
Simulador de transações E-Commerce

#Descrição

Este projeto é um aplicativo simples desenvolvido para Android que visa apenas simular uma transação de cartão de crédito utilizando uma API mockada através do Apiary e armazenar localmente as simulações realizadas. Além disso, o projeto visa demonstrar um arquitura baseada na arquitetura proposta pelo DDD aplicada em um projeto para a plataforma Android.

### Frameworks Utilizados ###
- AndroidAnnotations (view injection e processamento em background)
- RoboGuice (dependency injection)
- Spring Framework Rest (auxiliar para requisições a serviços REST)
- Jackson (serialização e deserialização de objetos em JSON)
- OrmLite (ORM para persistência de dados localmente utilizando SQLite)
- Fabric (Crashlytics para crash report e Awnsers para estatística de utilização)

### TO-DO ###
- Substituição do ORMLite pelo Realmente
- Tornar o banco de dados criptografado
- Incluir um mecanismo de segurança na comunicação com a API.
