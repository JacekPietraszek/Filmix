import { Category } from "./categories.model";

export interface VideoContent {
    id: number;
    title: string;
    releaseDate: string;
    image: Image;
    description: string;
    duration: number;
    episodes: number;
    actorMovieDtoList: ActorMovie[];
    type: string; // Dodane pole typu
    category: Category;
    movieCategories: MovieCategory[];
  }


  export interface Image {
    id: number;
    imageUrl: string;

  }

  export interface MovieCategory {
    id: number;
  }

 
  
  export interface ActorMovie {
    actor: Actor;
    actorId: number;
    roleName: string | null;
  }
  
 export interface Actor {
    
    firstName: string;
    lastName: string;
    birthday: string;
  }
  
  export default VideoContent;
  