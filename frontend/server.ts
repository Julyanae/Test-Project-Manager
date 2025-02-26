/******************************************************************
 * SERVER.TS
 ******************************************************************/

// Importations nécessaires pour le SSR
import 'zone.js/node';
import { APP_BASE_HREF } from '@angular/common';
import express from 'express';
import { join } from 'path';

// Importez l’express engine fournie par Angular Universal
//import { ngExpressEngine } from '@nguniversal/express-engine';
// Importez votre module de serveur (à générer via Angular Universal)
//import { AppServerModule } from './src/app/app.server.module';

export function app(): express.Express {
  const server = express();
  // Chemin vers le dossier contenant les fichiers générés (browser)
  const distFolder = join(process.cwd(), 'dist/your-project/browser'); // Remplacez "your-project" par le nom de votre projet
  const indexHtml = 'index.html';

  // Configurez l’express engine pour le rendu Angular
  server.engine('html', ngExpressEngine({
    bootstrap: AppServerModule,
  }));
  server.set('view engine', 'html');
  server.set('views', distFolder);

  // Servir les fichiers statiques (images, CSS, JS…)
  server.get('*.*', express.static(distFolder, {
    maxAge: '1y'
  }));

  // Toutes les autres routes utilisent Angular pour le rendu
  server.get('*', (req, res) => {
    res.render(indexHtml, {
      req,
      providers: [{ provide: APP_BASE_HREF, useValue: req.baseUrl }]
    });
  });

  return server;
}

function run(): void {
  const port = process.env.PORT || 4000;
  const server = app();
  server.listen(port, () => {
    console.log(`Node Express server listening on http://localhost:${port}`);
  });
}

run();
