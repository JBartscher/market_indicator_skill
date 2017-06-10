#!/bin/bash

set -eu
cd ..

if [ ! -d GraphicsLib ]; then
	# graphicslib
	wget https://bitbucket.org/DarkRevenant/graphicslib/downloads/GraphicsLib%201.2.0.7z
	7z x GraphicsLib*.7z
fi

if [ ! -d LazyLib ]; then
	# lazylib
	wget https://bitbucket.org/LazyWizard/lazylib/downloads/LazyLib%202.2.zip
	unzip LazyLib*.zip
fi

