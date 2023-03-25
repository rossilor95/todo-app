import { AppBar, Box, Toolbar, Typography } from "@mui/material";

export default function Header() {
  return (
    <Box sx={{ flexGrow: 1, position: "sticky" }}>
      <AppBar>
        <Toolbar>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            Todo Demo App
          </Typography>
        </Toolbar>
      </AppBar>
      <Toolbar />
    </Box>
  );
}
