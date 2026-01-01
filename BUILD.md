# Building and Testing AutoModpack

This guide explains how to build and test the AutoModpack mod from source.

---

## Prerequisites

Before building AutoModpack, ensure you have the following installed:

- **Java Development Kit (JDK) 17 or higher** (JDK 25 recommended)
  - You can download it from [Eclipse Temurin](https://adoptium.net/) or [Oracle](https://www.oracle.com/java/technologies/downloads/)
- **Git** (for cloning the repository)

The project uses Gradle, but you don't need to install it separately as the project includes the Gradle Wrapper (`gradlew`).

---

## Cloning the Repository

```bash
git clone https://github.com/Skidamek/AutoModpack.git
cd AutoModpack
```

---

## Building the Mod

### Build All Versions

To build all Minecraft versions and loaders (Fabric, Forge, NeoForge):

```bash
./gradlew build
```

On Windows:
```cmd
gradlew.bat build
```

The built mod files will be located in the `merged/` directory after the build completes.

### Build Specific Versions

To build only specific versions, you can target specific subprojects:

```bash
# Build only Fabric versions
./gradlew :1.21.11-fabric:build :1.21.10-fabric:build

# Build only NeoForge versions
./gradlew :1.21.11-neoforge:build :1.21.10-neoforge:build

# Build only the core module
./gradlew :core:build
```

### Clean Build

To clean previous build artifacts and perform a fresh build:

```bash
./gradlew clean build
```

---

## Running Tests

AutoModpack includes unit tests in the core module to verify functionality.

### Run All Tests

```bash
./gradlew core:test
```

### Run Specific Tests

To run a specific test class:

```bash
./gradlew core:test --tests "pl.skidam.automodpack_core.utils.CustomFileUtilsTest"
```

### View Test Results

After running tests, you can find detailed test reports at:
```
core/build/reports/tests/test/index.html
```

Open this file in a web browser to see detailed test results.

---

## Testing the Mod in Minecraft

To test the mod in an actual Minecraft environment:

### 1. Build the Mod

First, build the version you want to test:

```bash
./gradlew build
```

### 2. Locate the Built Mod

After building, find the `.jar` files in the `merged/` directory. The files are organized by Minecraft version and loader.

### 3. Install in Minecraft

#### For Testing on a Client:

1. Install the appropriate mod loader (Fabric, Forge, or NeoForge) for your Minecraft version
2. Copy the built `.jar` file to your Minecraft `mods` folder:
   - Windows: `%APPDATA%\.minecraft\mods\`
   - Linux/Mac: `~/.minecraft/mods/`
3. Launch Minecraft with the mod loader profile

#### For Testing on a Server:

1. Set up a Minecraft server with the appropriate mod loader
2. Copy the built `.jar` file to the server's `mods` folder
3. Start the server
4. Connect with a client that also has AutoModpack installed

### 4. Basic Testing Steps

1. **Server Setup:**
   - Start a server with AutoModpack installed
   - Verify that AutoModpack generates the initial modpack metadata in `~/automodpack/`
   
2. **Client Connection:**
   - Install AutoModpack on a client
   - Connect to the server
   - Verify the certificate fingerprint prompt appears
   - Accept the modpack download
   - Restart the client and verify the modpack loads correctly

3. **Update Testing:**
   - Add or remove a mod on the server
   - Restart the server
   - Connect with the client
   - Verify that AutoModpack detects and applies the update

---

## Common Build Issues

### Issue: "Plugin [id: 'fabric-loom'] was not found"

**Solution:** This usually indicates a network issue or Maven repository problem. Try:

1. Check your internet connection
2. Clear the Gradle cache:
   ```bash
   ./gradlew clean --refresh-dependencies
   ```
3. Delete the `.gradle` directory and rebuild:
   ```bash
   rm -rf .gradle/
   ./gradlew build
   ```

### Issue: "Unsupported Kotlin plugin version"

**Solution:** This is typically a warning and won't prevent the build. If it causes issues:

1. Ensure you're using JDK 17 or higher
2. Update Gradle wrapper if needed:
   ```bash
   ./gradlew wrapper --gradle-version=9.2.1
   ```

### Issue: Out of Memory

**Solution:** The project is configured to use 4GB of RAM. If you encounter memory issues:

1. Increase the memory in `gradle.properties`:
   ```properties
   org.gradle.jvmargs=-Xmx6G
   ```
2. Or set it temporarily:
   ```bash
   ./gradlew build -Dorg.gradle.jvmargs=-Xmx6G
   ```

### Issue: Build takes too long

**Solution:** The project builds multiple Minecraft versions. To speed up development:

1. Build only the version you need (see "Build Specific Versions" above)
2. Use Gradle's build cache (already enabled by default)
3. Use parallel builds (already enabled in `gradle.properties`)

---

## Development Tips

### Using an IDE

#### IntelliJ IDEA (Recommended):

1. Open the project root directory in IntelliJ IDEA
2. IDEA will automatically detect the Gradle project
3. Wait for Gradle to sync
4. You can now edit code with full IDE support

#### Eclipse:

1. Import the project as a Gradle project
2. Wait for the import to complete
3. The project structure should be ready to use

### Hot Reload Development

For faster testing during development, you can use Gradle's continuous build:

```bash
./gradlew -t build
```

This will automatically rebuild when you save changes.

### Code Style

The project follows standard Java/Kotlin conventions. Before submitting changes:

1. Ensure your code compiles without warnings
2. Run the tests to ensure nothing breaks
3. Follow the existing code style in the project

---

## Additional Resources

- **Documentation:** Check the `docs/` folder for detailed documentation
- **Contributing Guidelines:** See [CONTRIBUTING.md](CONTRIBUTING.md)
- **Discord:** Join our [Discord server](https://discord.gg/hS6aMyeA9P) for help
- **Issues:** Report bugs or request features on [GitHub Issues](https://github.com/Skidamek/AutoModpack/issues)

---

# Compilation et Test d'AutoModpack (Français)

Ce guide explique comment compiler et tester le mod AutoModpack depuis les sources.

## Prérequis

- **Java Development Kit (JDK) 17 ou supérieur** (JDK 25 recommandé)
- **Git**

## Compilation

### Compiler toutes les versions

```bash
./gradlew build
```

Les fichiers compilés seront dans le dossier `merged/`.

### Compiler des versions spécifiques

```bash
# Seulement Fabric
./gradlew :1.21.11-fabric:build

# Seulement le module core
./gradlew :core:build
```

## Exécuter les Tests

```bash
./gradlew core:test
```

Les rapports de test sont disponibles dans `core/build/reports/tests/test/index.html`.

## Tester le Mod dans Minecraft

1. **Compiler le mod:** `./gradlew build`
2. **Trouver le fichier .jar:** Dans le dossier `merged/`
3. **Installer:**
   - Copiez le fichier `.jar` dans votre dossier `mods` de Minecraft
   - Windows: `%APPDATA%\.minecraft\mods\`
   - Linux/Mac: `~/.minecraft/mods/`
4. **Lancer Minecraft** avec le mod loader approprié (Fabric, Forge, ou NeoForge)

### Test Serveur

1. Installez AutoModpack sur un serveur Minecraft
2. Démarrez le serveur pour générer les métadonnées
3. Connectez-vous avec un client ayant AutoModpack installé
4. Vérifiez que le modpack se télécharge et s'installe correctement

## Problèmes Courants

### Erreur de mémoire

Augmentez la mémoire dans `gradle.properties`:
```properties
org.gradle.jvmargs=-Xmx6G
```

### Compilation trop lente

Compilez uniquement la version dont vous avez besoin:
```bash
./gradlew :1.21.11-fabric:build
```

## Ressources

- **Documentation:** Dossier `docs/`
- **Discord:** [Serveur Discord](https://discord.gg/hS6aMyeA9P)
- **Issues:** [GitHub Issues](https://github.com/Skidamek/AutoModpack/issues)
