import babel from '@rolldown/plugin-babel';
import react, { reactCompilerPreset } from '@vitejs/plugin-react';
import { defineConfig } from 'vite';

// https://vite.dev/config/
export default defineConfig({
	base: '/boxy-backrooms/',
	appType: 'spa',
	resolve: { alias: { '@/': '/src/' } },
	plugins: [react(), babel({ presets: [reactCompilerPreset()] })],
	build: {
		rolldownOptions: {
			output: {
				codeSplitting: {
					groups: [
						{
							test: /node_modules/,
							name: "node_modules"
						}
					]
				}
			}
		}
	}
});
