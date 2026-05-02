package com.project.batch.constants

enum class TechStack(val code: String, val owner: String, val repo: String, val displayName: String, val en: String, val category: Category) {

    // Frontend
    REACT("REACT", "facebook", "react", "React", "React", Category.FRONTEND),
    NEXT_JS("NEXT_JS", "vercel", "next.js", "Next.js", "Next.js", Category.FRONTEND),
    VUE("VUE", "vuejs", "core", "Vue.js", "Vue.js", Category.FRONTEND),
    ANGULAR("ANGULAR", "angular", "angular", "Angular", "Angular", Category.FRONTEND),
    TYPESCRIPT("TYPESCRIPT", "microsoft", "TypeScript", "TypeScript", "TypeScript", Category.FRONTEND),
    VITE("VITE", "vitejs", "vite", "Vite", "Vite", Category.FRONTEND),
    TAILWIND_CSS("TAILWIND_CSS", "tailwindlabs", "tailwindcss", "Tailwind CSS", "Tailwind CSS", Category.FRONTEND),
    TANSTACK_QUERY("TANSTACK_QUERY", "TanStack", "query", "TanStack Query", "TanStack Query", Category.FRONTEND),
    SVELTE("SVELTE", "sveltejs", "svelte", "Svelte", "Svelte", Category.FRONTEND),
    NUXT_JS("NUXT_JS", "nuxt", "nuxt", "Nuxt.js", "Nuxt.js", Category.FRONTEND),
    ASTRO("ASTRO", "withastro", "astro", "Astro", "Astro", Category.FRONTEND),
    ZUSTAND("ZUSTAND", "pmndrs", "zustand", "Zustand", "Zustand", Category.FRONTEND),
    VITEST("VITEST", "vitest-dev", "vitest", "Vitest", "Vitest", Category.FRONTEND),

    // Backend
    SPRING_FRAMEWORK("SPRING_FRAMEWORK", "spring-projects", "spring-framework", "Spring Framework", "Spring Framework", Category.BACKEND),
    SPRING_BOOT("SPRING_BOOT", "spring-projects", "spring-boot", "Spring Boot", "Spring Boot", Category.BACKEND),
    SPRING_SECURITY("SPRING_SECURITY", "spring-projects", "spring-security", "Spring Security", "Spring Security", Category.BACKEND),
    SPRING_BATCH("SPRING_BATCH", "spring-projects", "spring-batch", "Spring Batch", "Spring Batch", Category.BACKEND),
    SPRING_DATA_JPA("SPRING_DATA_JPA", "spring-projects", "spring-data-jpa", "Spring Data JPA", "Spring Data JPA", Category.BACKEND),
    SPRING_DATA_MONGODB("SPRING_DATA_MONGODB", "spring-projects", "spring-data-mongodb", "Spring Data MongoDB", "Spring Data MongoDB", Category.BACKEND),
    SPRING_DATA_REDIS("SPRING_DATA_REDIS", "spring-projects", "spring-data-redis", "Spring Data Redis", "Spring Data Redis", Category.BACKEND),
    SPRING_AUTHORIZATION_SERVER("SPRING_AUTHORIZATION_SERVER", "spring-projects", "spring-authorization-server", "Spring Authorization Server", "Spring Authorization Server", Category.BACKEND),
    SPRING_SESSION("SPRING_SESSION", "spring-projects", "spring-session", "Spring Session", "Spring Session", Category.BACKEND),
    SPRING_KAFKA("SPRING_KAFKA", "spring-projects", "spring-kafka", "Spring Kafka", "Spring Kafka", Category.BACKEND),
    SPRING_AMQP("SPRING_AMQP", "spring-projects", "spring-amqp", "Spring AMQP", "Spring AMQP", Category.BACKEND),
    SPRING_GRAPHQL("SPRING_GRAPHQL", "spring-projects", "spring-graphql", "Spring GraphQL", "Spring GraphQL", Category.BACKEND),
    SPRING_INTEGRATION("SPRING_INTEGRATION", "spring-projects", "spring-integration", "Spring Integration", "Spring Integration", Category.BACKEND),
    SPRING_CLOUD_GATEWAY("SPRING_CLOUD_GATEWAY", "spring-cloud", "spring-cloud-gateway", "Spring Cloud Gateway", "Spring Cloud Gateway", Category.BACKEND),
    SPRING_CLOUD_CONFIG("SPRING_CLOUD_CONFIG", "spring-cloud", "spring-cloud-config", "Spring Cloud Config", "Spring Cloud Config", Category.BACKEND),
    SPRING_CLOUD_OPENFEIGN("SPRING_CLOUD_OPENFEIGN", "spring-cloud", "spring-cloud-openfeign", "Spring Cloud OpenFeign", "Spring Cloud OpenFeign", Category.BACKEND),
    NEST_JS("NEST_JS", "nestjs", "nest", "NestJS", "NestJS", Category.BACKEND),
    FASTAPI("FASTAPI", "fastapi", "fastapi", "FastAPI", "FastAPI", Category.BACKEND),
    DJANGO("DJANGO", "django", "django", "Django", "Django", Category.BACKEND),
    EXPRESS("EXPRESS", "expressjs", "express", "Express", "Express", Category.BACKEND),
    KAFKA("KAFKA", "apache", "kafka", "Kafka", "Kafka", Category.BACKEND),
    REDIS("REDIS", "redis", "redis", "Redis", "Redis", Category.BACKEND),
    FLASK("FLASK", "pallets", "flask", "Flask", "Flask", Category.BACKEND),
    HONO("HONO", "honojs", "hono", "Hono", "Hono", Category.BACKEND),
    GIN("GIN", "gin-gonic", "gin", "Gin", "Gin", Category.BACKEND),
    FIBER("FIBER", "gofiber", "fiber", "Fiber", "Fiber", Category.BACKEND),
    PRISMA("PRISMA", "prisma", "prisma", "Prisma", "Prisma", Category.BACKEND),

    // DevOps
    DOCKER("DOCKER", "moby", "moby", "Docker", "Docker", Category.DEVOPS),
    KUBERNETES("KUBERNETES", "kubernetes", "kubernetes", "Kubernetes", "Kubernetes", Category.DEVOPS),
    TERRAFORM("TERRAFORM", "hashicorp", "terraform", "Terraform", "Terraform", Category.DEVOPS),
    AWS_CDK("AWS_CDK", "aws", "aws-cdk", "AWS CDK", "AWS CDK", Category.DEVOPS),
    HELM("HELM", "helm", "helm", "Helm", "Helm", Category.DEVOPS),
    ARGO_CD("ARGO_CD", "argoproj", "argo-cd", "ArgoCD", "ArgoCD", Category.DEVOPS),
    GRAFANA("GRAFANA", "grafana", "grafana", "Grafana", "Grafana", Category.DEVOPS),
    PROMETHEUS("PROMETHEUS", "prometheus", "prometheus", "Prometheus", "Prometheus", Category.DEVOPS),
    ISTIO("ISTIO", "istio", "istio", "Istio", "Istio", Category.DEVOPS),
    FLUX("FLUX", "fluxcd", "flux2", "Flux", "Flux", Category.DEVOPS),
    KUSTOMIZE("KUSTOMIZE", "kubernetes-sigs", "kustomize", "Kustomize", "Kustomize", Category.DEVOPS),

    // Language
    PYTHON("PYTHON", "python", "cpython", "Python", "Python", Category.LANGUAGE),
    GO("GO", "golang", "go", "Go", "Go", Category.LANGUAGE),
    RUST("RUST", "rust-lang", "rust", "Rust", "Rust", Category.LANGUAGE),
    KOTLIN("KOTLIN", "JetBrains", "kotlin", "Kotlin", "Kotlin", Category.LANGUAGE),
    RUBY("RUBY", "ruby", "ruby", "Ruby", "Ruby", Category.LANGUAGE),
    ZIG("ZIG", "ziglang", "zig", "Zig", "Zig", Category.LANGUAGE),
    ELIXIR("ELIXIR", "elixir-lang", "elixir", "Elixir", "Elixir", Category.LANGUAGE),

    // AI
    LANGCHAIN("LANGCHAIN", "langchain-ai", "langchain", "LangChain", "LangChain", Category.AI),
    LANGGRAPH("LANGGRAPH", "langchain-ai", "langgraph", "LangGraph", "LangGraph", Category.AI),
    LLAMA_INDEX("LLAMA_INDEX", "run-llama", "llama_index", "LlamaIndex", "LlamaIndex", Category.AI),
    TRANSFORMERS("TRANSFORMERS", "huggingface", "transformers", "Transformers", "Transformers", Category.AI),
    PYTORCH("PYTORCH", "pytorch", "pytorch", "PyTorch", "PyTorch", Category.AI),
    OLLAMA("OLLAMA", "ollama", "ollama", "Ollama", "Ollama", Category.AI),
    CREWAI("CREWAI", "crewaiinc", "crewai", "CrewAI", "CrewAI", Category.AI),
    OPENAI_PYTHON("OPENAI_PYTHON", "openai", "openai-python", "OpenAI Python", "OpenAI Python", Category.AI),
    VLLM("VLLM", "vllm-project", "vllm", "vLLM", "vLLM", Category.AI),
    LITELLM("LITELLM", "BerriAI", "litellm", "LiteLLM", "LiteLLM", Category.AI),
    AUTOGEN("AUTOGEN", "microsoft", "autogen", "AutoGen", "AutoGen", Category.AI),
    DSPY("DSPY", "stanfordnlp", "dspy", "DSPy", "DSPy", Category.AI),
    PYDANTIC_AI("PYDANTIC_AI", "pydantic", "pydantic-ai", "Pydantic AI", "Pydantic AI", Category.AI),
    ;

    companion object {
        fun fromCode(code: String): TechStack =
            entries.firstOrNull { it.code == code }
                ?: throw IllegalArgumentException("Invalid tech stack code: $code")
    }

    fun releaseUrl(tagName: String) = "https://github.com/$owner/$repo/releases/tag/$tagName"
}