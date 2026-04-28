import './styles/App.css';
import { useState } from 'react';
import modules from "./styles/Modules.module.css"

enum Pages {
	Home,
}

function App() {
	const [page, setPage] = useState(Pages.Home);

	return (
		<>
			<div id='navbar' className={modules.navbar}>
				<a href='#' onClick={() => {
					setPage(Pages.Home)
				}}>Home</a>
			</div>
			{page == Pages.Home ? <><section id='center'>
				<div>
					<h1 style={{
						fontFamily: "Comic"
					}}>Boxy's Backrooms</h1>
				</div>
			</section>

				<p>The backrooms is a dangerous place outside of reality. However, if you're careful, it can also be a gold mine.</p>
				<section id='next-steps'>
					<div id='social'>
						<h2>Connect with us</h2>
						<ul>
							<li>
								<a
									href='https://github.com/BoxyPlayz/boxy-backrooms'
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
									href='https://discord.gg/T9abqkRdk6'
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

			</> : null
			}
		</>
	);
}

export default App;
