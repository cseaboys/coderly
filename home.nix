 #~/.config/home-manager/home.nix
{ config, pkgs, ... }:

{
  # Basic Home Manager setup
  home.username = "nixos";
  home.homeDirectory = "/home/nixos";
  home.stateVersion = "24.05";

#Configure the fish shell and integrate zoxide
  programs.fish = {
    enable = true;
    shellInit = ''
      # Initialize zoxide for fish shell integration.
      eval (zoxide init fish)
    '';
  };

  # Install the desired packages.
  home.packages = with pkgs; [
    fd            # Fast alternative to 'find'
    ripgrep       # Fast text search tool
    neovim        # Modern Vim-based text editor
    zellij        # Terminal workspace manager
    python312     # Python 3.12 interpreter
    git           # Version control system
    gh            # GitHub CLI
    openjdk23     # OpenJDK 23 (verify attribute if needed)
    clang         # C language compiler frontend
    zoxide        # Enhanced directory navigation
    fzf           # Fuzzy finder
    bat           # 'cat' clone with syntax highlighting
    nodejs        # Node.js runtime
    deno          # Secure runtime for JS/TS
    bun           # Bun JavaScript runtime (if available)
    micro         # Terminal-based text editor
    python3
    python3Packages.numpy
    python3Packages.matplotlib
    python3Packages.seaborn
    python3Packages.pandas
    python3Packages.scikit-learn
    python3Packages.mlxtend
    python3Packages.jupyter
    python3Packages.ipykernel
  ];

  # Allow Home Manager to manage itself.
  programs.home-manager.enable = true;
}