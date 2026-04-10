
import reactLogo from './assets/react.svg';
import viteLogo from './assets/vite.svg';
import './styles/App.css';
import comic from "./assets/ComicRelief.ttf";

function App() {
	return (
		<>
			<section id='center'>
				<div>
					<h1 style={{
						font: comic
					}}>Boxy's Backrooms</h1>
				</div>
			</section>

			<section id='next-steps'>
				<div id='docs'>
					<svg
						className='icon'
						role='presentation'
						aria-hidden='true'>
						<use href='/icons.svg#documentation-icon'></use>
					</svg>
					<h2>Resources</h2>
					<ul>
						<li>
							<a
								href='https://vite.dev/'
								target='_blank'>
								<img
									className='logo'
									src={viteLogo}
									alt=''
								/>
								Explore Vite
							</a>
						</li>
						<li>
							<a
								href='https://react.dev/'
								target='_blank'>
								<img
									className='button-icon'
									src={reactLogo}
									alt=''
								/>
								Learn more
							</a>
						</li>
					</ul>
				</div>
				<div id='social'>
					<h2>Connect with us</h2>
					<ul>
						<li>
							<a
								href='https://github.com/vitejs/vite'
								target='_blank'>
								<svg
									className='button-icon'
									role='presentation'
									aria-hidden='true'>
									<use href='/boxy-backrooms/icons.svg#github-icon'></use>
								</svg>
								GitHub
							</a>
						</li>
						<li>
							<a
								href='https://chat.vite.dev/'
								target='_blank'>
								<svg
									className='button-icon'
									role='presentation'
									aria-hidden='true'>
									<use href='/boxy-backrooms/icons.svg#discord-icon'></use>
								</svg>
								Discord
							</a>
						</li>
					</ul>
				</div>
			</section>

			<section id='spacer' />
		</>
	);
}

export default App;
